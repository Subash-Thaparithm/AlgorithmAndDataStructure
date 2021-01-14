package Generic;

public class BinarySearch_RotatedArray {
    int Target = 0;

    public int search(int[] nums, int target) { // Must be O(n)
        Target = target;
        if (nums.length == 0) return -1;
        else if (nums.length == 1 && nums[0] != target) return -1;
        else if (nums.length == 1 && nums[0] == target) return 0;
        else if (nums.length == 2 && nums[0] == target) return 0;
        else if (nums.length == 2 && nums[1] == target) return 1;
        else if (nums.length == 2) return -1;

        //Use binary search to find the point of 2 sorted subarrays within. It is always the smallest element as pivot.
        boolean isMinIndexFound = false;

        int low = 0;
        int high = nums.length - 1;
        int minIndex = (low + high) / 2; // Initialise to middle element
        //Condition for no rotation i.e first element is minimum.
        if (nums[0] < nums[nums.length - 1]) {
            minIndex = 0;
            isMinIndexFound = true;
        }

        while (!isMinIndexFound && minIndex >= 0 && minIndex <= nums.length - 1) {
            minIndex = (low + high) / 2;
            // Check if element (mid+1) is minimum element. Consider  the cases like {3, 4, 5, 1, 2}
            if (minIndex < high && nums[minIndex + 1] < nums[minIndex]) {
                minIndex = minIndex + 1;
                break;
            }
            // Check if mid itself is minimum element
            if (minIndex > low && nums[minIndex] < nums[minIndex - 1]) break;
            // The minimum element is the only element whose previous is greater than it.
            if (nums[minIndex] <= nums[minIndex - 1])
                break; // Current index is the minimum element or pivot for rotation.
            else {
                if (nums[minIndex] <= nums[nums.length - 1]) {
                    high = minIndex;
                } //If middle element is smaller than last element, then the minimum element lies in left half.
                else {
                    low = minIndex;
                } // Else minimum element lies in right half.
            }
        }

        low = 0;
        high = minIndex - 1;
        //Use binary search to find the element in either of the sorted sub array. Use separate search for it. Normal binary search.
        int targetIndex = binarySearch(nums, low, high);
        if (targetIndex != -1) return targetIndex;

        low = minIndex;
        high = nums.length - 1;
        targetIndex = binarySearch(nums, low, high);

        return targetIndex;
    }

    public int binarySearch(int[] nums, int low, int high) { // returns index
        if (high < low) return -1;
        int midIndex = (low + high) / 2;

        if (Target == nums[midIndex]) return midIndex;

        else if (Target < nums[midIndex]) {
            high = midIndex - 1;
        } else if (Target > nums[midIndex]) {
            low = midIndex + 1;
        }
        return binarySearch(nums, low, high);
    }

    public int minElementRotatedArray(int[] nums) {
        if (nums.length == 0) return -1;
        else if (nums.length == 1) return nums[0];
        //Use binary search to find the point of 2 sorted subarrays within. It is always the smallest element as pivot.
        boolean isMinIndexFound = false;

        int low = 0;
        int high = nums.length - 1;
        int minIndex = (low + high) / 2; // Initialise to middle element
        //Condition for no rotation i.e first element is minimum.
        if (nums[0] < nums[nums.length - 1]) {
            minIndex = 0;
            isMinIndexFound = true;
        }

        while (!isMinIndexFound && minIndex >= 0 && minIndex <= nums.length - 1) {
            minIndex = (low + high) / 2;
            // Check if element (mid+1) is minimum element. Consider  the cases like {3, 4, 5, 1, 2}
            if (minIndex < high && nums[minIndex + 1] < nums[minIndex]) {
                minIndex = minIndex + 1;
                break;
            }
            // Check if mid itself is minimum element
            if (minIndex > low && nums[minIndex] < nums[minIndex - 1]) break;
            // The minimum element is the only element whose previous is greater than it.
            if (nums[minIndex] <= nums[minIndex - 1])
                break; // Current index is the minimum element or pivot for rotation.
            else {
                if (nums[minIndex] <= nums[nums.length - 1]) {
                    high = minIndex;
                } //If middle element is smaller than last element, then the minimum element lies in left half.
                else {
                    low = minIndex;
                } // Else minimum element lies in right half.
            }
        }
        return nums[minIndex];
    }
}
