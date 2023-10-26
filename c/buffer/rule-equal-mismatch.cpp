// License: MIT (c) GitLab Inc.

#include <iostream>
#include <algorithm>

int main() {
    int arr1[] = {1, 2, 3, 4, 5};
    int arr2[] = {5, 4, 3, 2, 1};

    // ruleid: c_buffer_rule-equal-mismatch
    bool areEqual = std::equal(std::begin(arr1), std::end(arr1), std::begin(arr2));

    // ruleid: c_buffer_rule-equal-mismatch
    auto mismatchResult = std::mismatch(std::begin(arr1), std::end(arr1), std::begin(arr2));

    // ruleid: c_buffer_rule-equal-mismatch
    bool isPermutation = std::is_permutation(std::begin(arr1), std::end(arr1), std::begin(arr2));

    return 0;
}
