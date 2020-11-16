package leetcode

import (
	"reflect"
	"sort"
	"testing"
)

func TestFourSum(t *testing.T) {
	data := map[int][]int{
		6189: []int{-493, -482, -482, -456, -427, -405, -392, -385, -351, -269, -259, -251, -235, -235, -202, -201, -194, -189, -187, -186, -180, -177, -175, -156, -150, -147, -140, -122, -112, -112, -105, -98, -49, -38, -35, -34, -18, 20, 52, 53, 57, 76, 124, 126, 128, 132, 142, 147, 157, 180, 207, 227, 274, 296, 311, 334, 336, 337, 339, 349, 354, 363, 372, 378, 383, 413, 431, 471, 474, 481, 492},
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
	// sort.Ints(nums)
	v := [][]int{}
	// cache := map[string]bool{}
	for i := 0; i < l; i++ {
		for j := 0; j < l; j++ {
			if j == i {
				j++
			}
			if j >= l {
				continue
			}
			for x := 0; x < l; x++ {
				for ; eqij(i, j, x); x++ {

				}
				if x >= l {
					continue
				}
				for y := 0; y < l; y++ {
					for ; eqijx(i, j, x, y); y++ {

					}
					if y >= l {
						continue
					}
					tv := []int{nums[i], nums[j], nums[x], nums[y]}
					sort.Ints(tv)
					// cKey := strconv.Itoa(tv[0]) + "_" + strconv.Itoa(tv[1]) + "_" + strconv.Itoa(tv[2]) + "_" + strconv.Itoa(tv[3])
					// if _, ok := cache[cKey]; ok {
					// 	continue
					// }
					if test(tv[0], tv[1], tv[2], tv[3], v, tv, target) {
						v = append(v, tv)
						// cache[cKey] = true
					} else {
						// cache[cKey] = false
					}
				}
			}
		}
	}
	return v
}

func eqij(i int, j int, c int) bool {
	if i == c {
		return true
	}
	if j == c {
		return true
	}
	return false
}

func eqijx(i int, j int, x int, c int) bool {
	if i == c {
		return true
	}
	if j == c {
		return true
	}
	if x == c {
		return true
	}
	return false
}

func test(i int, j int, x int, y int, a [][]int, v []int, t int) bool {
	if (i + j + x + y) == t {
		for _, at := range a {
			if intEq(at, v) {
				return false
			}
		}
		return true
	}
	return false
}

func intEq(a []int, b []int) bool {
	for i := 0; i < 4; i++ {
		if a[i] != b[i] {
			return false
		}
	}
	return true
}
