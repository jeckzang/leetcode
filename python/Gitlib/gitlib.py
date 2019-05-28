import re
import sys
import requests
import gitlab

TOKEN="TLqy7e5zKzAeEA7B-Dws"

def protectBranch():
    # private token or personal token authentication
    gl = gitlab.Gitlab('https://onecode.polycom-labs.com', private_token=TOKEN)

    # locked project except
    exp = {"ucs-engine-service","common-libs-pdms","common-libs-shared","api-rprm"}
    # locked branch name
    lockedBranchName = {"develop"}

    # list all the projects
    projects = gl.projects.list()
    for project in projects:
        if project.namespace["name"] == "resourcemanager":
            if project.name not in exp:
                print(project.name +" will be locked!")
                branches = project.branches.list()
                for b in branches:
                    if b.name in lockedBranchName:
                        print("lock branch:%s"%(b.name))
                        b.protect()

def main():
   protectBranch()

if __name__ == "__main__":
    main()

                