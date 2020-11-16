package leetcode

import (
	"testing"
)

func TestLetterCombinations(t *testing.T) {
	td := letterCombinations("")
	if len(td) != 0 {
		t.Fail()
	}
	data := map[string][]string{
		"2":     []string{"a", "b", "c"},
		"7":     []string{"p", "q", "r", "s"},
		"23":    []string{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"},
		"1":     []string{},
		"0":     []string{},
		"44444": []string{},
	}
	for k, v := range data {
		td := letterCombinations(k)
		if len(td) != len(v) {
			t.Fail()
		}
		for _, tv := range v {
			found := false
			for _, tdv := range td {
				if tv == tdv {
					found = true
				}
			}
			if !found {
				t.Fail()
			}
		}
	}
}

var dMap = map[int32]string{
	50: "abc",
	51: "def",
	52: "ghi",
	53: "jkl",
	54: "mno",
	55: "pqrs",
	56: "tuv",
	57: "wxyz",
}

func letterCombinations(digits string) []string {
	if len(digits) > 4 {
		return []string{}
	}
	result := []string{}
	for _, v := range digits {
		if v < 50 || v > 57 {
			return []string{}
		}
		result = multi(result, dMap[v])
	}
	return result
}

func multi(result []string, strs string) []string {
	nr := []string{}
	for _, c := range strs {
		if len(result) == 0 {
			nr = append(nr, string(c))
		} else {
			for _, v := range result {
				nr = append(nr, v+string(c))
			}
		}
	}
	return nr
}
