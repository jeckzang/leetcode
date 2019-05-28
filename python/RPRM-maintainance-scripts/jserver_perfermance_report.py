import sys
import json
import re
import datetime
from dateutil.parser import parse

def default(o):
    if isinstance(o, (datetime.date, datetime.datetime)):
        return o.isoformat()

def retrieveBasicInfo(fileName):
    foundStartTime = False  
    lastLine = "" 
    counterMap = {} 
    with open(fileName) as f:
        for line in f:
            if not foundStartTime:
                timeArrays = re.split(' ',line)
                print ("start time:%s"%(timeArrays[1]))
                counterMap["start-time"] =parse(timeArrays[1])                
                foundStartTime = True                
            if "DEBUG" in line and "is-debug" not in counterMap:
                counterMap["is-debug"] = True
            lastLine = line
    timeArrays = re.split(' ',lastLine)
    print ("end time:%s"%(timeArrays[1]))
    counterMap["end-time"] =parse(timeArrays[1])         
    return counterMap

def findInFile(fileName,strs) :    
    counterMap = {}    
    with open(fileName) as f:
        for line in f:           
            for str in strs:
                if str in line:                                       
                    if str in counterMap:
                       counterMap[str] = counterMap[str] +1
                    else:
                       counterMap[str] = 1 
    return counterMap

def jserverLog(fileName):
    # retrieve times
    basicInfos  = retrieveBasicInfo(fileName)
    print ("basic info:%s" %(json.dumps(basicInfos,sort_keys=True,indent=1,default=default)))
    seconds = (basicInfos["end-time"]-basicInfos["start-time"]).seconds
    print ("time in seconds=%d"%(seconds))

    # VC2 provisioning
    vc2ProvisonKeies = {"RuleEngine-----fired rules","HTTP Request URI -> /PlcmRmWeb/device/"}
    countForVc2Provisioning  = findInFile(fileName,vc2ProvisonKeies)    
    print ("count for VC2 provisioning:%s" %(json.dumps(countForVc2Provisioning,sort_keys=True,indent=1,default=default)))

    # UC provisioning    
    ucProvisionKeies = {"provision file"}
    countForUCProvisioning = findInFile(fileName,ucProvisionKeies)
    print ("count for UC provision:%s" %(json.dumps(countForUCProvisioning,sort_keys=True,indent=1,default=default)))

    # Conference
    conferenceKeies = {"DMA participant notification"}
    countForConferenceKeies = findInFile(fileName,conferenceKeies)
    print ("count for conference:%s" %(json.dumps(countForConferenceKeies,sort_keys=True,indent=1,default=default)))

    # DB connection
    dbConnectionKeies = {"Caused by: org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection"}
    countForDbConnectionKeies = findInFile(fileName,dbConnectionKeies)
    print ("count for DB info:%s" %(json.dumps(countForDbConnectionKeies,sort_keys=True,indent=1,default=default)))

def countRate(fileName, strs):
    counterMap = {}
    timesMap = {}    
    with open(fileName) as f:
        for line in f:           
            for str in strs:
                if str in line:  
                    m = re.search(strs[str],line)                                     
                    c = m.group(1)
                    if str in counterMap:
                       counterMap[str] = counterMap[str] +float(c)
                       timesMap[str] = timesMap[str]+1
                    else:
                       counterMap[str] = float(c)
                       timesMap[str] = 1 
    for str in counterMap:
        counterMap[str] = counterMap[str]/timesMap[str]
    return counterMap

def performanceLog(fileName):
     # CPU
    cpuKeies = {"Cpu(s):":"([0-9]+\\.*[0-9]*)%us",
        "Mem:":"([0-9]+)k used",
        "Port 8443":"connections : ([0-9]+)",
        "Port 443":"connections : ([0-9]+)",
        "Port 389":"connections : ([0-9]+)",
        "Port 3601":"connections : ([0-9]+)",
        "FD amount of JBoss process":"([0-9]+)",
        "FD amount of UCS process":"([0-9]+)",
        "FD amount of ALARM process":"([0-9]+)",
        "FD amount of LICENSE process":"([0-9]+)",
        "FD amount of UMC process":"([0-9]+)"}
    cpuRates = countRate(fileName,cpuKeies)
    print ("cpu info:%s" %(json.dumps(cpuRates,sort_keys=True,indent=1,default=default)))



def main():
    # print command line arguments
    for arg in sys.argv[1:]:
        print (arg)
    if "Jserver" in sys.argv[1]:
        jserverLog(sys.argv[1])
    
    if "performance" in sys.argv[1]:
        performanceLog(sys.argv[1])

if __name__ == "__main__":
    main()




