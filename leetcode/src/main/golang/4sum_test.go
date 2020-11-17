package leetcode

import (
	"reflect"
	"sort"
	"testing"
)

func TestFourSum(t *testing.T) {
	data := map[int][]int{
		-14: []int{2, -4, -5, -2, -3, -5, 0, 4, -2},
	}
	values := map[int][][]int{
		0: [][]int{[]int{-2, -1, 1, 2}, []int{-2, 0, 0, 2}, []int{-1, 0, 0, 1}},
	}
	for k, v := range data {
		value := fourSum(v, k)
		if len(value) != len(values[k]) {
			t.Fail()
		}
		var exist = false
		for _, vv := range values[k] {
			for _, tv := range value {
				if reflect.DeepEqual(tv, vv) {
					exist = true
					break
				}
			}
		}
		if !exist {
			t.Fail()
		}

	}
}

// var cpuprofile = flag.String("cpuprofile", "", "write cpu profile to `file`")
// var memprofile = flag.String("memprofile", "", "write memory profile to `file`")

// func main() {
// 	flag.Parse()
// 	if *cpuprofile != "" {
// 		f, err := os.Create(*cpuprofile)
// 		if err != nil {
// 			log.Fatal("could not create CPU profile: ", err)
// 		}
// 		defer f.Close() // error handling omitted for example
// 		if err := pprof.StartCPUProfile(f); err != nil {
// 			log.Fatal("could not start CPU profile: ", err)
// 		}
// 		defer pprof.StopCPUProfile()
// 	}
// 	data := map[int][]int{
// 		6189: []int{-493, -482, -482, -456, -427, -405, -392, -385, -351, -269, -259, -251, -235, -235, -202, -201, -194, -189, -187, -186, -180, -177, -175, -156, -150, -147, -140, -122, -112, -112, -105, -98, -49, -38, -35, -34, -18, 20, 52, 53, 57, 76, 124, 126, 128, 132, 142, 147, 157, 180, 207, 227, 274, 296, 311, 334, 336, 337, 339, 349, 354, 363, 372, 378, 383, 413, 431, 471, 474, 481, 492},
// 	}
// 	for k, v := range data {
// 		fourSum(v, k)
// 	}
// }

func fourSum(nums []int, target int) [][]int {
	l := len(nums)
	if l == 0 {
		return [][]int{}
	}
	sort.Ints(nums)
	v := [][]int{}
	for i := 0; i < l; i++ {
		for j := i + 1; j < l; j++ {
			for x := j + 1; x < l; x++ {
				for y := x + 1; y < l; y++ {
					ok, tv, bigger := test(nums[i], nums[j], nums[x], nums[y], v, target)
					if ok {
						v = append(v, tv)
					}
					if bigger {
						break
					}
				}
			}
		}
	}
	return v
}

func test(i int, j int, x int, y int, a [][]int, t int) (bool, []int, bool) {
	if (i + j + x + y) == t {
		v := []int{i, j, x, y}
		for _, at := range a {
			if intEq(at, v) {
				return false, nil, false
			}
		}
		return true, v, false
	} else if (i + j + x + y) > t {
		return false, nil, true
	}
	return false, nil, false
}

func intEq(a []int, b []int) bool {
	t := []int{}
	wrong := 0
	for i := 0; i < 4; i++ {
		for j := 0; j < 4; j++ {
			var found = false
			for x := 0; x < len(t); x++ {
				if j == t[x] {
					found = true
					break
				}
			}
			if found {
				continue
			}
			if a[i] == b[j] {
				t = append(t, j)
				break
			} else if i > 2 && wrong > 2 {
				return false
			} else {
				wrong++
			}
		}
	}
	if len(t) == 4 {
		return true
	}
	return false
}
