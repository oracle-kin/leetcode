# 算法模式模板速查（PATTERNS.md）

> 从本仓库 `src/` 下 17 个包、200 道题的实际实现中提炼的通用模板。
> 变量命名与代码风格与仓库实现保持一致（对撞用 `left`/`right`，快慢用 `slow`/`fast`，
> 链表哨兵一律 `dummy`，DP 数组一律 `dp`，滚动变量 `prev2`/`prev1`/`cur`）。

## 目录

1. [Arrays & Hashing](#1-arrays--hashing)
2. [Two Pointers](#2-two-pointers)
3. [Sliding Window](#3-sliding-window)
4. [Stack / Monotonic Stack](#4-stack--monotonic-stack)
5. [Binary Search](#5-binary-search)
6. [Linked List](#6-linked-list)
7. [Trees & BST](#7-trees--bst)
8. [Heap / Priority Queue](#8-heap--priority-queue)
9. [Backtracking](#9-backtracking)
10. [Dynamic Programming](#10-dynamic-programming)
11. [Graphs](#11-graphs)
12. [Greedy](#12-greedy)
13. [Intervals](#13-intervals)
14. [Math & Geometry](#14-math--geometry)
15. [Bit Manipulation](#15-bit-manipulation)
16. [Trie](#16-trie)
17. [String](#17-string)

---

## 1. Arrays & Hashing

**识别信号**：查找配对/判重/分组 → 哈希；矩阵遍历 → 边界模拟；要求 O(1) 空间 → 复用输入数组。

### 模板 A：单遍哈希查询/判重（1, 217, 36）

```java
// 变体1: HashMap 记录已见元素及其信息（Two Sum 型）
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (map.containsKey(complement)) {
        return new int[] { map.get(complement), i };
    }
    map.put(nums[i], i);
}

// 变体2: HashSet 判重，利用 add 返回值
Set<Integer> seen = new HashSet<>();
for (int num : nums) {
    if (!seen.add(num)) return true;   // add 失败 = 已存在
}
return false;
```

### 模板 B：螺旋矩阵四边界收缩（54, 59）

```java
int top = 0, bottom = matrix.length - 1;
int left = 0, right = matrix[0].length - 1;

while (top <= bottom && left <= right) {
    for (int c = left; c <= right; c++) { /* 访问 matrix[top][c] */ }
    top++;
    for (int r = top; r <= bottom; r++) { /* 访问 matrix[r][right] */ }
    right--;
    if (top <= bottom) {
        for (int c = right; c >= left; c--) { /* 访问 matrix[bottom][c] */ }
        bottom--;
    }
    if (left <= right) {
        for (int r = bottom; r >= top; r--) { /* 访问 matrix[r][left] */ }
        left++;
    }
}
```

### 模板 C：O(1) 空间原地技巧（41, 73, 169, 238）

核心思想：用输入/输出数组本身或常数个变量代替哈希表。

```java
// 循环排序 cyclic sort（41）：把值 x 换到下标 x-1，再扫描找缺口
for (int i = 0; i < n; i++) {
    while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
        swap(nums, i, nums[i] - 1);
    }
}
for (int i = 0; i < n; i++) {
    if (nums[i] != i + 1) return i + 1;
}

// Boyer-Moore 摩尔投票（169）：计数抵消
int candidate = nums[0], count = 0;
for (int num : nums) {
    if (count == 0) candidate = num;
    count += (num == candidate) ? 1 : -1;
}

// 前缀 × 后缀（238）：answer 存前缀积，逆序补乘后缀积
int[] answer = new int[n];
answer[0] = 1;
for (int i = 1; i < n; i++) answer[i] = answer[i - 1] * nums[i - 1];
int suffix = 1;
for (int i = n - 1; i >= 0; i--) {
    answer[i] *= suffix;
    suffix *= nums[i];
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 哈希查询/判重 | 1 Two Sum、217 Contains Duplicate、36 Valid Sudoku（rows/cols/boxes 三组 Set，`boxIdx = (r/3)*3 + c/3`） |
| B 螺旋边界收缩 | 54 Spiral Matrix（读）、59 Spiral Matrix II（写 `num++`） |
| C 原地 O(1) 技巧 | 41 First Missing Positive、73 Set Matrix Zeroes（首行首列做标记位）、169 Majority Element、238 Product Except Self |

---

## 2. Two Pointers

**识别信号**：有序数组求和 → 对撞；原地删除/去重 → 同向覆写；链表判环/中点/倒数第 k → 快慢指针。

### 模板 A：对撞双指针（167, 15, 16, 18, 125）

```java
int left = 0, right = nums.length - 1;
while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == target) {
        // 求全部解时（15/18）需跳过重复：
        // while (left < right && nums[left] == nums[left + 1]) left++;
        // while (left < right && nums[right] == nums[right - 1]) right--;
        // left++; right--;
        return new int[]{left + 1, right + 1};
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

> 3Sum/4Sum = 排序 + 外层 for 固定前缀 + 内层对撞；固定元素同样要跳过重复。

对撞反转子模板（31, 189 三次反转法）：

```java
private void reverse(int[] nums, int left, int right) {
    while (left < right) {
        swap(nums, left, right);
        left++;
        right--;
    }
}
```

### 模板 B：同向快慢指针原地覆写（26, 27, 80）

```java
// slow 标记写入位，fast 扫描读取位
int slow = 0;
for (int fast = 0; fast < nums.length; fast++) {
    if (/* nums[fast] 应保留：
           27: nums[fast] != val
           26: nums[fast] != nums[slow]   (slow 从 0、fast 从 1 起)
           80: nums[fast] != nums[slow-2] (slow、fast 均从 2 起) */) {
        nums[slow] = nums[fast];
        slow++;
    }
}
return slow; // 新长度
```

### 模板 C：三指针分区 / 荷兰国旗（75）

```java
int low = 0, mid = 0, high = nums.length - 1;
while (mid <= high) {
    if (nums[mid] == 0) {
        swap(nums, low, mid);
        low++; mid++;
    } else if (nums[mid] == 1) {
        mid++;
    } else { // nums[mid] == 2
        swap(nums, mid, high);
        high--;   // mid 不动，换来的元素待检
    }
}
```

### 模板 D：Floyd 快慢指针判环 / 找中点（141, 142, 202, 143, 148, 234）

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) {
        // 142 第二阶段：entry 从 head 与 slow 同速走，相遇处即环入口
        ListNode entry = head;
        while (entry != slow) { entry = entry.next; slow = slow.next; }
        return entry;
    }
}
// 找中点：去掉相遇判断，循环结束后 slow 即中点
```

### 模板 E：固定间距快慢指针 —— 倒数第 n 个（19）

```java
ListNode dummy = new ListNode(0, head);
ListNode fast = dummy, slow = dummy;
for (int i = 0; i <= n; i++) fast = fast.next; // fast 先走 n+1 步
while (fast != null) { fast = fast.next; slow = slow.next; }
slow.next = slow.next.next; // slow 恰停在待删结点前
return dummy.next;
```

### 模板 F：找中点 + 反转后半 + 处理两半（143, 234；148 为中点+归并变体）

```java
// Step 1: 找中点（模板 D）
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }

// Step 2: 反转后半（与 206 同款）
ListNode prev = null, curr = slow.next;
slow.next = null;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}

// Step 3: 143 交错合并两半；234 双指针逐值比较
```

### 模板 G：双尾指针逆向合并（88）

```java
int p1 = m - 1, p2 = n - 1, p = m + n - 1;
while (p2 >= 0) {
    if (p1 >= 0 && nums1[p1] > nums2[p2]) nums1[p--] = nums1[p1--];
    else                                  nums1[p--] = nums2[p2--];
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 对撞 | 167、15、16、18（排序+固定前缀）、125（跳过非字母数字）、31、189、151 |
| B 同向覆写 | 27、26、80（条件逐级放宽的一族） |
| C 三指针分区 | 75 Sort Colors |
| D Floyd 判环/中点 | 141、142、202（数字序列当隐式链表）、143、148、234 |
| E 固定间距 | 19 Remove Nth From End |
| F 中点+反转+合并 | 143 Reorder List、234 Palindrome Linked List；148 Sort List（中点+递归+merge） |
| G 逆向合并 | 88 Merge Sorted Array |
| 其他 | 28（朴素串匹配）、61（成环再断）、82/86（dummy 系，见链表）、160（双指针换轨 `pa = pa == null ? headB : pa.next`） |

---

## 3. Sliding Window

**识别信号**：连续子数组/子串的最长、最短、计数问题。求最长 → 非法时收缩；求最短 → 满足时收缩。

### 模板 A：可变窗口（核心模板，4/6 题）

```java
// 求最长（3）：窗口非法时收缩
Set<Character> set = new HashSet<>();
int left = 0, maxLen = 0;
for (int right = 0; right < s.length(); right++) {
    while (set.contains(s.charAt(right))) {   // 窗口非法
        set.remove(s.charAt(left));
        left++;
    }
    set.add(s.charAt(right));
    maxLen = Math.max(maxLen, right - left + 1);
}
return maxLen;

// 求最短（209 / 76）：窗口满足时收缩并记录
int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
for (int right = 0; right < nums.length; right++) {
    sum += nums[right];                       // 右端进窗
    while (sum >= target) {                   // 76: matched == target.size()
        minLen = Math.min(minLen, right - left + 1);
        sum -= nums[left];                    // 左端出窗
        left++;
    }
}
return minLen == Integer.MAX_VALUE ? 0 : minLen;
```

> 76 Minimum Window Substring 的窗口状态升级为 `target`/`window` 双频次 map + `matched`
> 计数器（避免每次全量比较 map）；30 是它的按词步进版（`right += wordLen`，外层枚举偏移）。

### 模板 B：固定大小窗口（219, 239）

```java
// 219：HashSet 维护最近 k 个元素
Set<Integer> set = new HashSet<>();
for (int i = 0; i < nums.length; i++) {
    if (i > k) set.remove(nums[i - k - 1]);   // 移出窗口外元素
    if (!set.add(nums[i])) return true;       // 窗口内查询
}

// 239：单调递减双端队列（存下标），队首即窗口最大值
Deque<Integer> deque = new ArrayDeque<>();
for (int i = 0; i < n; i++) {
    if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) deque.pollFirst(); // 出窗
    while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast(); // 保持单调
    deque.offerLast(i);
    if (i >= k - 1) result[i - k + 1] = nums[deque.peekFirst()];
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 可变窗口·求最长 | 3（状态 = HashSet） |
| A 可变窗口·求最短 | 209（状态 = sum）、76（双 map + matched）、30（按词步进变体） |
| B 固定窗口 | 219（HashSet 判重）、239（单调队列求最大） |

---

## 4. Stack / Monotonic Stack

**识别信号**：括号/嵌套匹配、表达式求值、"下一个更大/更小元素"、直方图面积。

### 模板 S1：匹配栈（20）

```java
Stack<Character> stack = new Stack<>();
for (char ch : s.toCharArray()) {
    if (isOpen(ch)) {
        stack.push(ch);
    } else {
        if (stack.isEmpty()) return false;
        if (!matches(stack.pop(), ch)) return false;
    }
}
return stack.isEmpty();   // 全部消解才合法
```

### 模板 S2：单调栈——直方图/下一个更小元素（84；85 逐行复用）

```java
int n = heights.length;
Stack<Integer> stack = new Stack<>();     // 栈中下标对应高度单调递增
int best = 0;
for (int i = 0; i <= n; i++) {
    int h = (i == n) ? 0 : heights[i];    // 哨兵：末尾追加高度 0 强制清栈
    while (!stack.isEmpty() && h < heights[stack.peek()]) {
        int height = heights[stack.pop()];                       // 被弹元素是区间内最矮
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;  // 左界=新栈顶+1，右界=i-1
        best = Math.max(best, height * width);
    }
    stack.push(i);
}
```

### 模板 S3：表达式求值栈（150, 224, 227）

```java
// 变体 a — 后缀表达式（150）
Stack<Integer> stack = new Stack<>();
for (String token : tokens) {
    if (isOperator(token)) {
        int b = stack.pop(), a = stack.pop();   // 后弹的是左操作数
        stack.push(apply(a, b, token));
    } else {
        stack.push(Integer.parseInt(token));
    }
}
return stack.pop();

// 变体 b — 带括号加减（224）：'(' 时压现场，')' 时恢复
int result = 0, sign = 1, num = 0;
for (char ch : s.toCharArray()) {
    if (Character.isDigit(ch))      num = num * 10 + (ch - '0');
    else if (ch == '+')  { result += sign * num; num = 0; sign = 1; }
    else if (ch == '-')  { result += sign * num; num = 0; sign = -1; }
    else if (ch == '(')  { stack.push(result); stack.push(sign); result = 0; sign = 1; }
    else if (ch == ')')  { result += sign * num; num = 0;
                           result *= stack.pop();     // '(' 前的符号
                           result += stack.pop(); }   // '(' 前的结果
}
return result + sign * num;

// 变体 c — 无括号四则运算（227）：记住上一个运算符，*/ 立即与栈顶结算
int num = 0; char sign = '+';
for (int i = 0; i < s.length(); i++) {
    char ch = s.charAt(i);
    if (Character.isDigit(ch)) num = num * 10 + (ch - '0');
    if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
        switch (sign) {
            case '+': stack.push(num); break;
            case '-': stack.push(-num); break;
            case '*': stack.push(stack.pop() * num); break;
            case '/': stack.push(stack.pop() / num); break;
        }
        sign = ch; num = 0;
    }
}
// 最后把栈内所有值求和
```

### 模板 S5：双容器设计题（155, 225, 232）

```java
// 155 Min Stack：辅助栈同步维护最小值
public void push(int val) {
    stack.push(val);
    if (minStack.isEmpty() || val <= minStack.peek()) minStack.push(val); // <= 处理重复最小值
}
public void pop() {
    if (stack.pop().equals(minStack.peek())) minStack.pop(); // equals 避免 Integer 缓存陷阱
}

// 232 双栈模拟队列：输出栈空时一次性 transfer，均摊 O(1)
public int pop()  { if (output.isEmpty()) transfer(); return output.pop(); }
private void transfer() { while (!input.isEmpty()) output.push(input.pop()); }

// 225 双队列模拟栈：push 时倒转 + 交换引用
public void push(int x) {
    q2.offer(x);
    while (!q1.isEmpty()) q2.offer(q1.poll());
    Queue<Integer> temp = q1; q1 = q2; q2 = temp;
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| S1 匹配栈 | 20 Valid Parentheses |
| S2 单调栈 | 84 Largest Rectangle；85 Maximal Rectangle（dp 包，逐行直方图 + 本模板）；239 单调队列同族 |
| S3 表达式求值 | 150（后缀）、224（带括号）、227（乘除优先） |
| S4 栈模拟序列 | 71 Simplify Path（`..` 弹栈、目录入栈） |
| S5 双容器设计 | 155、225、232 |

---

## 5. Binary Search

**识别信号**：有序/部分有序查找；"最大化最小值/最小化最大值" → 答案空间二分。
统一写法：`int mid = left + (right - left) / 2;` 防溢出。

**两种循环条件的分工**：
- `while (left <= right)`：闭区间精确查找，找到即返回，`left = mid + 1` / `right = mid - 1` 排除 mid；退出后 `left` = 插入点（upper bound），`right` = floor。
- `while (left < right)`：收敛到唯一候选（边界/最小值/峰值），保留侧用 `right = mid` 或 `left = mid`（后者 mid 必须右偏 `+1` 防死循环）。

### 模板 A：精确查找（35, 74, 69）

```java
int left = 0, right = nums.length - 1;          // 闭区间 [left, right]
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] == target) return mid;
    else if (nums[mid] < target) left = mid + 1;
    else right = mid - 1;
}
return left;    // 35: 插入点；69: return right（最后一个满足条件的值）；未找到: -1
// 74: 二维摊平 matrix[mid / n][mid % n]
```

### 模板 B：边界二分（34）

```java
// 左边界（lower bound）：mid 天然左偏，保留候选用 right = mid
while (left < right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] < target) left = mid + 1;
    else right = mid;                            // mid 可能是答案，保留
}
// 退出时 left == right（需再验证 nums[left] == target）

// 右边界：mid 必须右偏 +1，否则 left = mid 死循环
while (left < right) {
    int mid = left + (right - left) / 2 + 1;     // bias to the right
    if (nums[mid] > target) right = mid - 1;
    else left = mid;
}
```

### 模板 C：旋转数组精确查找（33, 81）

```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] == target) return mid;

    // 81 专属：三值相等无法判断有序半边，退化线性收缩
    // if (nums[left] == nums[mid] && nums[mid] == nums[right]) { left++; right--; continue; }

    if (nums[left] <= nums[mid]) {                            // 左半有序
        if (nums[left] <= target && target < nums[mid]) right = mid - 1;
        else left = mid + 1;
    } else {                                                  // 右半有序
        if (nums[mid] < target && target <= nums[right]) left = mid + 1;
        else right = mid - 1;
    }
}
return -1;
```

### 模板 D：旋转求最小 / 峰值（153, 154, 162）

```java
int left = 0, right = nums.length - 1;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] > nums[right]) left = mid + 1;   // 最小值在右半，排除 mid
    else if (nums[mid] < nums[right]) right = mid; // 最小值在左半含 mid，保留
    else right--;                                  // 154 专属：相等时缩右端
}
return nums[left];
// 162 峰值变体：比较对象换成右邻居
// if (nums[mid] < nums[mid + 1]) left = mid + 1; else right = mid;
```

### 模板 E：答案空间二分（69, 4）

```java
// 在值域而非下标上二分（69 Sqrt(x)）
int left = 1, right = x / 2;
while (left <= right) {
    int mid = left + (right - left) / 2;
    long square = (long) mid * mid;              // long 防溢出
    if (square == x) return mid;
    else if (square < x) left = mid + 1;
    else right = mid - 1;
}
return right;                                    // 最大的满足 check 的答案
// 4 Median of Two Sorted Arrays：对切分点二分，check = 交叉 <= 关系，边界用 MIN/MAX_VALUE 哨兵
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 精确查找 `l <= r` | 35（返回 left）、74（摊平索引）、69（值域，返回 right） |
| B 边界二分 `l < r` | 34 First and Last Position |
| C 旋转精确查找 | 33、81（+三值相等退化收缩） |
| D 旋转最小/峰值 | 153、154（+`right--`）、162 |
| E 答案空间二分 | 69、4 |
| 非二分伴生 | 240（右上角阶梯搜索 O(m+n)）、268（XOR 消对） |

---

## 6. Linked List

**识别信号**：头结点可能变动 → dummy 哨兵；反转 → prev/curr/next 三指针；O(1) get/put 设计 → HashMap + 双向链表。

### 模板 L1：dummy 哨兵 + curr 构建/删除（本包最普遍骨架）

```java
// 构建新链（2, 21, 23, 147, 117）
ListNode dummy = new ListNode(0);
ListNode curr = dummy;
while (/* 还有输入 */) {
    curr.next = /* 选中的结点或 new ListNode(...) */;
    curr = curr.next;
}
return dummy.next;

// 删除型（203）：dummy 挂原链前，操作 curr.next
ListNode dummy = new ListNode(0, head);
ListNode curr = dummy;
while (curr.next != null) {
    if (curr.next.val == val) {
        curr.next = curr.next.next;  // 删除，curr 不动
    } else {
        curr = curr.next;
    }
}
return dummy.next;
```

### 模板 L2：迭代反转（206；143/234/25 复用）

```java
ListNode prev = null;
ListNode curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
return prev;
```

区间反转的头插法变体（92；25 的分组反转是它的推广）：

```java
ListNode dummy = new ListNode(0, head);
ListNode prev = dummy;
for (int i = 1; i < left; i++) prev = prev.next; // prev 停在区间前一个
ListNode curr = prev.next;
for (int i = 0; i < right - left; i++) {         // 每轮把 next 头插到 prev 后
    ListNode next = curr.next;
    curr.next = next.next;
    next.next = prev.next;
    prev.next = next;
}
return dummy.next;
```

### 模板 L3：merge 两个有序链表（21；23 用堆推广到 k 路；148 复用）

```java
ListNode dummy = new ListNode(0);
ListNode curr = dummy;
while (list1 != null && list2 != null) {
    if (list1.val <= list2.val) {
        curr.next = list1;
        list1 = list1.next;
    } else {
        curr.next = list2;
        list2 = list2.next;
    }
    curr = curr.next;
}
curr.next = (list1 != null) ? list1 : list2;  // 接上剩余段
return dummy.next;
```

### 模板 L4：fast/slow 找中点（带 prev 断链）（109, 148）

```java
ListNode slow = head, fast = head, prev = null;
while (fast != null && fast.next != null) {
    prev = slow;
    slow = slow.next;
    fast = fast.next.next;
}
if (prev != null) prev.next = null; // 断成两半，slow 为后半起点/中点
```

### 模板 L5：双向链表 + 双哨兵（设计题：146 LRU Cache）

```java
// head/tail 哨兵互指；HashMap + remove/addToHead 两个 O(1) 原语
head = new Node(0, 0);
tail = new Node(0, 0);
head.next = tail;
tail.prev = head;

private void remove(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
}
private void addToHead(Node node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
}
```

### 模板 L6：局部重接（24 两两交换；25 = L6 锚点 + L2 组内反转）

```java
ListNode dummy = new ListNode(0, head);
ListNode prev = dummy;
while (prev.next != null && prev.next.next != null) {
    ListNode first = prev.next;
    ListNode second = first.next;
    first.next = second.next;
    second.next = first;
    prev.next = second;
    prev = first;
}
return dummy.next;
```

### 题目归类

| 模板 | 题目 |
|---|---|
| L1 dummy 哨兵 | 2（逐位加+carry）、21、23、147、117、203；锚点：24、25、92 |
| L2 迭代反转 | 206（含递归版）、25（组内）、92（头插法）、147（反复头插） |
| L3 merge | 21、23（PriorityQueue k 路）、148 |
| L4 找中点断链 | 109 Sorted List to BST、148 Sort List |
| L5 双向链表哨兵 | 146 LRU Cache |
| L6 局部重接 | 24、25 |
| 特殊技巧 | 138（三趟交织复制 random 指针）、237（值拷贝伪删除）、114（逆前序+成员 prev）、116（利用上层 next O(1) 连接）、83（头不变故无需 dummy） |

---

## 7. Trees & BST

**识别信号**：求树的属性（深度/平衡/LCA）→ 自底向上后序；沿路径传状态（路径和/区间约束）→ 自顶向下带参；逐层输出 → BFS size 快照；BST → 利用中序有序性。

仓库中 `TreeNode` 为各文件独立的 `static class`（三构造器：无参 / val / val+left+right）。

### 模板 A：自底向上后序 DFS（104, 110, 226, 236）

```java
// 从子树取信息，合成当前节点答案；可用哨兵值（如 -1）短路
public int dfs(TreeNode node) {
    if (node == null) return 0;             // base case
    int left  = dfs(node.left);
    int right = dfs(node.right);
    return 1 + Math.max(left, right);       // 合并逻辑随题变化
}

// 236 普通二叉树 LCA：返回"找到的节点"，左右均非空即当前节点是 LCA
private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);
    if (left != null && right != null) return root;
    return (left != null) ? left : right;
}
```

### 模板 B：自顶向下 DFS，参数携带路径状态（98, 112, 129）

```java
// 状态（约束区间 / 剩余和 / 累积数字）沿参数向下传，叶子处收敛
private int dfs(TreeNode node, int carry) {
    if (node == null) return 0;
    carry = update(carry, node.val);        // 如 carry*10+val（129）或 target-val（112）
    if (node.left == null && node.right == null) return carry;  // 叶子判定
    return dfs(node.left, carry) + dfs(node.right, carry);      // 或 || / &&
}
// 98 Validate BST：携带 Integer min/max 区间，null 表示无界
```

### 模板 C：双树同步/镜像递归（100, 101）

```java
private boolean compare(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;
    if (t1.val != t2.val) return false;
    return compare(t1.left, t2.left) && compare(t1.right, t2.right);  // 同向（100）
    // 镜像版（101）：compare(t1.left, t2.right) && compare(t1.right, t2.left)
}
```

### 模板 D：中序遍历 + 成员变量状态（94, 99, 230）

```java
private TreeNode prev = null;   // 或 int count / int result
private void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    /* 访问逻辑：与 prev 比较（99 找逆序对）/ 计数到 k（230） */
    prev = node;
    inorder(node.right);
}
```

### 模板 E：分治建树（105, 106, 108）

```java
Map<Integer, Integer> inMap = new HashMap<>();      // inorder 值 → 下标，O(1) 查根
for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);

private TreeNode build(int[] arr, int L, int R /*, ...*/) {
    if (L > R) return null;
    TreeNode root = new TreeNode(arr[rootIdx]);     // pre 头（105）/ post 尾（106）/ 数组中点（108）
    int inIdx = inMap.get(root.val);                // 划分左右子树大小
    root.left  = build(/* 左区间 */);
    root.right = build(/* 右区间 */);
    return root;
}
```

### 模板 F：BFS 逐层遍历——size 快照（102, 103, 107, 111, 199）

```java
List<List<Integer>> result = new ArrayList<>();
if (root == null) return result;
Queue<TreeNode> q = new LinkedList<>();
q.offer(root);
while (!q.isEmpty()) {
    int size = q.size();                    // 关键：快照当前层大小
    List<Integer> level = new ArrayList<>();
    for (int i = 0; i < size; i++) {
        TreeNode cur = q.poll();
        level.add(cur.val);                 // ← 变体点全在这一行
        if (cur.left  != null) q.offer(cur.left);
        if (cur.right != null) q.offer(cur.right);
    }
    result.add(level);
}
```

变体：103 zigzag（`LinkedList` + `addFirst/addLast` 交替）、107（`result.add(0, level)` 头插）、
111（遇第一个叶子直接返回 depth）、199 右视图（只取 `i == size - 1`）。

### 模板 G：迭代栈遍历（144 前序, 145 后序）

```java
Deque<TreeNode> stack = new ArrayDeque<>();
stack.push(root);
while (!stack.isEmpty()) {
    TreeNode cur = stack.pop();
    result.add(cur.val);                        // 前序尾插；后序改 result.add(0, cur.val)
    if (cur.right != null) stack.push(cur.right);   // 前序：先右后左
    if (cur.left  != null) stack.push(cur.left);    // 后序：先左后右（"根右左"的逆序）
}
```

### 模板 H：BST 迭代下行（235 BST 的 LCA）

```java
TreeNode cur = root;
while (cur != null) {
    if (p.val < cur.val && q.val < cur.val)      cur = cur.left;
    else if (p.val > cur.val && q.val > cur.val) cur = cur.right;
    else return cur;                             // 分叉点即 LCA，O(h) 时间 O(1) 空间
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 自底向上后序 | 104、110（-1 哨兵）、226、236；124（dp 包树形 DP 同型） |
| B 自顶向下带参 | 98、112、129 |
| C 双树同步/镜像 | 100、101 |
| D 中序+状态 | 94、99、230 |
| E 分治建树 | 105、106、108 |
| F BFS 逐层 | 102、103、107、111、199 |
| G 迭代栈遍历 | 144、145 |
| H BST 迭代下行 | 235 |

---

## 8. Heap / Priority Queue

**识别信号**：Top-K / 第 K 大小 / 流式维护极值。口诀：**求第 k 大用最小堆，求第 k 小用最大堆**，堆大小恒为 k。

### 模板 H1：固定大小 k 的堆求 Top-K（215）

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();   // 最小堆
    for (int num : nums) {
        heap.offer(num);
        if (heap.size() > k) heap.poll();   // 保持堆大小为 k，O(n log k)
    }
    return heap.peek();                     // 堆顶 = 第 k 大
}
// 求第 k 小 → 最大堆 new PriorityQueue<>((a, b) -> b - a)
```

### 题目归类

| 用法 | 题目 |
|---|---|
| Top-K | 215 Kth Largest |
| 堆维护结束时间 | 253 Meeting Rooms II（interval 包） |
| k 路归并 | 23 Merge k Sorted Lists（linkedlist 包） |

---

## 9. Backtracking

**识别信号**：枚举所有子集/组合/排列/切分/放置方案。
统一风格：辅助方法名 `backtrack`（网格类为 `dfs`），收集时 `new ArrayList<>(curr)` 深拷贝，撤销用 `curr.remove(curr.size() - 1)`。

### 模板 A：子集型——start 索引，进入即收集（78, 90）

```java
private void backtrack(List<List<Integer>> result, List<Integer> curr, int[] nums, int start) {
    result.add(new ArrayList<>(curr));          // 每个节点都是一个子集，无 return

    for (int i = start; i < nums.length; i++) {
        // 去重版（90，需先 Arrays.sort）：
        // if (i > start && nums[i] == nums[i - 1]) continue;
        curr.add(nums[i]);
        backtrack(result, curr, nums, i + 1);   // i+1：不重复使用当前元素
        curr.remove(curr.size() - 1);
    }
}
```

### 模板 B：组合求和型——start 索引 + remain 剪枝（39, 40, 77）

```java
private void backtrack(List<List<Integer>> result, List<Integer> curr,
                       int[] candidates, int remain, int start) {
    if (remain < 0) return;                     // 剪枝
    if (remain == 0) {
        result.add(new ArrayList<>(curr));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        // 40（每数一次+去重，需排序）：if (i > start && candidates[i] == candidates[i-1]) continue;
        curr.add(candidates[i]);
        backtrack(result, curr, candidates, remain - candidates[i],
                  i /* 39 可重复取传 i；40 传 i+1 */);
        curr.remove(curr.size() - 1);
    }
}
// 77 Combinations 同族：终止条件换成 curr.size() == k
```

### 模板 C：排列型——used[] 数组（46, 47）

```java
private void backtrack(List<List<Integer>> result, List<Integer> curr, int[] nums, boolean[] used) {
    if (curr.size() == nums.length) {
        result.add(new ArrayList<>(curr));
        return;
    }
    for (int i = 0; i < nums.length; i++) {     // 每层从 0 开始扫全部
        if (used[i]) continue;
        // 47 去重（需先排序）：同值且前一个在本层未被使用则跳过
        // if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
        used[i] = true;
        curr.add(nums[i]);
        backtrack(result, curr, nums, used);
        curr.remove(curr.size() - 1);
        used[i] = false;
    }
}
```

### 模板 D：去重惯用法——排序 + 同层跳过（40, 47, 90 共用的核心一行）

```java
Arrays.sort(nums);                                            // 入口先排序
// 组合/子集（start 型）:
if (i > start && nums[i] == nums[i - 1]) continue;
// 排列（used 型）:
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
```

### 模板 E：网格 DFS / 单词搜索——原地标记 '#'（79, 212）

```java
private boolean dfs(char[][] board, int i, int j, String word, int idx) {
    if (idx == word.length()) return true;                    // 成功条件放最前
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
    if (board[i][j] != word.charAt(idx)) return false;

    char temp = board[i][j];
    board[i][j] = '#';                                        // 原地标记代替 visited 数组
    boolean found = dfs(board, i + 1, j, word, idx + 1)
                 || dfs(board, i - 1, j, word, idx + 1)       // 四方向短路或
                 || dfs(board, i, j + 1, word, idx + 1)
                 || dfs(board, i, j - 1, word, idx + 1);
    board[i][j] = temp;                                       // 恢复现场
    return found;
}
// 212 变体：参数换成 TrieNode，node.word != null 时收集并置 null 防重复
```

### 模板 F：N 皇后 / 约束满足（51, 52, 37）

```java
// 52 的 O(1) 冲突检测版：三个 boolean 数组
private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
    if (row == n) { count++; return; }
    for (int col = 0; col < n; col++) {
        int d1 = row - col + n - 1;                           // 主对角线索引
        int d2 = row + col;                                   // 副对角线索引
        if (cols[col] || diag1[d1] || diag2[d2]) continue;
        cols[col] = diag1[d1] = diag2[d2] = true;
        backtrack(row + 1, n, cols, diag1, diag2);
        cols[col] = diag1[d1] = diag2[d2] = false;
    }
}
// 37 数独同风格：boolean 返回值短路 —— if (solve(board)) return true; board[i][j] = '.';
```

### 模板 G：字符串构建型——StringBuilder 回溯（17, 93）

```java
// 17：追加单字符，deleteCharAt 撤销
sb.append(c);
backtrack(result, sb, digits, idx + 1);
sb.deleteCharAt(sb.length() - 1);

// 93：追加变长段，记录长度后 setLength 撤销
int sbLen = sb.length();
sb.append(segment).append('.');
backtrack(result, sb, s, idx + len, parts + 1);
sb.setLength(sbLen);
```

### 模板 H：树/图路径收集——先 add 后递归再 remove（113, 126）

```java
path.add(node.val);
if (/* 到达终点条件 */) result.add(new ArrayList<>(path));
else { /* 递归各分支 */ }
path.remove(path.size() - 1);
// 126 特殊：先 BFS 建 adj + distance 层号，回溯只走 dist[next] == dist[cur]+1 的边
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 子集 | 78；90（+D 去重） |
| B 组合求和 | 39（传 i 可重复）、40（传 i+1 +去重）、77 |
| C 排列 | 46；47（+D 去重） |
| E 网格 DFS | 79；212（+Trie） |
| F 约束满足 | 51、52、37 |
| G StringBuilder 构串 | 17、93 |
| H 树/图路径 | 113、126 |
| 非回溯 | 89 Gray Code（公式 `i ^ (i >> 1)`） |

---

## 10. Dynamic Programming

**识别信号**：求最值/计数/可行性，且大问题可由重叠子问题组合。
仓库风格：凡二维可压缩的全部压成一维滚动数组，凡一维只依赖前两项的全部压成 `prev2`/`prev1` 双变量。

### 模式 1：Fibonacci 型一维 DP（70, 91）

```java
// dp[i] 只依赖 dp[i-1]、dp[i-2] → 双变量
if (n <= 2) return n;
int prev2 = 1, prev1 = 2;
for (int i = 3; i <= n; i++) {
    int cur = prev1 + prev2;       // 91：按单字符/双字符合法性条件累加
    prev2 = prev1;
    prev1 = cur;
}
return prev1;
```

### 模式 2：打家劫舍型——选/不选（198, 213）

```java
int prev2 = nums[0];
int prev1 = Math.max(nums[0], nums[1]);
for (int i = 2; i < nums.length; i++) {
    int cur = Math.max(prev1, prev2 + nums[i]);  // 不抢 vs 抢当前
    prev2 = prev1;
    prev1 = cur;
}
return prev1;
// 213 环形：拆两段 robRange(nums,0,n-2) 与 robRange(nums,1,n-1) 取 max
```

### 模式 3：Kadane 及变体（53, 152, 121）

```java
// 标准 Kadane（53）
int maxSum = nums[0], curSum = nums[0];
for (int i = 1; i < nums.length; i++) {
    curSum = Math.max(nums[i], curSum + nums[i]);  // 重开 vs 延续
    maxSum = Math.max(maxSum, curSum);
}

// 乘积变体（152）：负数翻转极值，同时维护 curMax/curMin
int tempMax = Math.max(num, Math.max(curMax * num, curMin * num));
curMin = Math.min(num, Math.min(curMax * num, curMin * num));
curMax = tempMax;
```

### 模式 4：二维网格 DP——一维滚动压缩（62, 63, 64, 120）

```java
// 计数型（62；63 加障碍判断 dp[j]=0）
int[] dp = new int[n];
for (int j = 0; j < n; j++) dp[j] = 1;      // 第一行基例
for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
        dp[j] += dp[j - 1];                 // dp[j] 旧值=上方, dp[j-1]=左方
    }
}
return dp[n - 1];

// 最值型（64）
dp[0] = grid[0][0];
for (int j = 1; j < n; j++) dp[j] = dp[j - 1] + grid[0][j];
for (int i = 1; i < m; i++) {
    dp[0] += grid[i][0];
    for (int j = 1; j < n; j++) {
        dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
    }
}
// 120 Triangle：自底向上原地 triangle[r][c] += min(下, 右下)
```

### 模式 5：二维字符串 DP（10, 44, 72, 97, 115）

统一骨架：`dp[m+1][n+1]`，`dp[0][0]` 为空串基例，先初始化第 0 行/列，字符一律 `charAt(i-1)/charAt(j-1)`。

```java
// 完整二维表（10 正则 / 44 通配符）
boolean[][] dp = new boolean[m + 1][n + 1];
dp[0][0] = true;
for (int j = 1; j <= n; j++) {
    if (p.charAt(j - 1) == '*') dp[0][j] = /* 10: dp[0][j-2]; 44: dp[0][j-1] */;
}
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
        if (pc == '*') {
            dp[i][j] = /* 含 dp[i-1][j] / dp[i][j-1] / dp[i][j-2] 的转移 */;
        } else {
            dp[i][j] = (pc == '?' /* 或 '.' */ || pc == sc) && dp[i - 1][j - 1];
        }
    }
}

// 一维压缩 + prev 保存对角线（72 编辑距离；221 同法）
int[] dp = new int[n + 1];
for (int j = 0; j <= n; j++) dp[j] = j;
for (int i = 1; i <= m; i++) {
    int prev = dp[0];                            // dp[i-1][j-1]
    dp[0] = i;
    for (int j = 1; j <= n; j++) {
        int temp = dp[j];                        // 旧 dp[j] = dp[i-1][j]
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[j] = prev;
        } else {
            dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), prev);
        }
        prev = temp;
    }
}

// 一维压缩 + j 逆序（115，避免覆盖 dp[i-1][j-1]）
for (int i = 1; i <= m; i++)
    for (int j = n; j >= 1; j--)
        if (s.charAt(i - 1) == t.charAt(j - 1)) dp[j] += dp[j - 1];
```

### 模式 6：回文 DP（5, 131, 132）

```java
// 中心扩展（5；132 套 min-cut）
private int expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--; right++;
    }
    return right - left - 1;
}

// 回文表预处理（131）：j 外层递增保证 isPal[i+1][j-1] 已算好
boolean[][] isPal = new boolean[n][n];
for (int j = 0; j < n; j++)
    for (int i = 0; i <= j; i++)
        if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPal[i + 1][j - 1]))
            isPal[i][j] = true;

// 最小切割（132）：dp[i] 初始化 i-1（dp[0] = -1 哨兵），对每个中心奇偶两次扩展
// dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
```

### 模式 7：Word Break 前缀分割（139, 140）

```java
Set<String> dict = new HashSet<>(wordDict);
int maxLen = 0;
for (String w : wordDict) maxLen = Math.max(maxLen, w.length());
boolean[] dp = new boolean[n + 1];
dp[0] = true;
for (int i = 1; i <= n; i++) {
    for (int j = Math.max(0, i - maxLen); j < i; j++) {   // maxLen 剪枝
        if (dp[j] && dict.contains(s.substring(j, i))) {
            dp[i] = true;
            break;
        }
    }
}
return dp[n];
// 140 输出方案：记忆化 DFS，Map<Integer, List<String>> 按起点缓存后缀句子
```

### 模式 8：状态机 / 股票 DP（121, 122, 123, 188）

```java
// 四状态机（123）；k 笔可推广为数组
int buy1 = Integer.MIN_VALUE, sell1 = 0;
int buy2 = Integer.MIN_VALUE, sell2 = 0;
for (int price : prices) {
    buy1 = Math.max(buy1, -price);
    sell1 = Math.max(sell1, buy1 + price);
    buy2 = Math.max(buy2, sell1 - price);
    sell2 = Math.max(sell2, buy2 + price);
}
return sell2;
// 188：k >= n/2 退化为 122 贪心；否则 dp[t][d] = max(dp[t][d-1], prices[d] + maxPrev)，
//      maxPrev = max(maxPrev, dp[t-1][d] - prices[d])，O(k*n)
```

### 模式 9：区间切分 / Catalan 型（96, 95, 87, 22）

```java
// Catalan 计数（96）
int[] dp = new int[n + 1];
dp[0] = 1;
dp[1] = 1;
for (int i = 2; i <= n; i++) {
    for (int j = 1; j <= i; j++) {
        dp[i] += dp[j - 1] * dp[i - j];   // 左子树 × 右子树
    }
}
// 95：区间记忆化枚举根，List<TreeNode>[][] memo
// 87：Boolean[n][n][n+1] 三维 memo，先字符计数剪枝，再枚举切分点试交换/不交换
```

### 模式 10：树形 DP（124）

```java
// 后序 DFS + 全局变量，负分支用 max(0, ...) 截断
private int dfs(TreeNode node) {
    if (node == null) return 0;
    int left = Math.max(0, dfs(node.left));
    int right = Math.max(0, dfs(node.right));
    maxSum = Math.max(maxSum, left + right + node.val);  // 拱形路径更新全局
    return node.val + Math.max(left, right);             // 只能返回单支给父节点
}
```

### 模式 11："以 i 结尾"定义型（32）

```java
// dp[i] = 以 i 结尾的最长有效括号
int[] dp = new int[n];
for (int i = 1; i < n; i++) {
    if (s.charAt(i) == ')') {
        if (s.charAt(i - 1) == '(') {
            dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
        } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
            dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
}
```

### 模式 12：矩阵形状 DP（221, 85）

```java
// 最大正方形（221）：1 + min(上, 左, 左上)，与模式 5 的 prev/temp 压缩手法一致
int[] dp = new int[n + 1];
int maxSide = 0, prev = 0;
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        int temp = dp[j];
        if (matrix[i - 1][j - 1] == '1') {
            dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), prev);
            maxSide = Math.max(maxSide, dp[j]);
        } else {
            dp[j] = 0;
        }
        prev = temp;
    }
}
return maxSide * maxSide;
// 85 最大矩形：heights[j] = matrix[i][j]=='1' ? heights[j]+1 : 0，每行调用单调栈（见 §4 模板 S2）
```

### 题目归类

| 子模式 | 题目 |
|---|---|
| 1 Fibonacci 型 | 70、91 |
| 2 打家劫舍型 | 198、213 |
| 3 Kadane 及变体 | 53、152、（121 同族） |
| 4 网格 DP（一维滚动） | 62、63、64、120、118、119 |
| 5 二维字符串 DP | 10、44、72、97、115 |
| 6 回文 DP | 5、131、132 |
| 7 Word Break | 139、140 |
| 8 状态机/股票 | 121、122、123、188 |
| 9 区间切分/Catalan | 96、95、87、22 |
| 10 树形 DP | 124 |
| 11 "以 i 结尾" | 32 |
| 12 矩阵形状 | 221、85 |
| DP 标签实际贪心/双指针 | 42（双指针 leftMax/rightMax）、45、55（贪心跳跃）、122 |

> 注：仓库尚未覆盖的经典子模式：0/1 背包、完全背包/零钱兑换（322/518）、LIS（300）、区间 DP 戳气球（312）、LCS（1143）。

---

## 11. Graphs

**识别信号**：网格连通块 → 网格 DFS；课程依赖/字典序 → 拓扑排序；连通性/成环判定 → Union-Find。
仓库惯例：邻接表用 `List<List<Integer>>` 预填空列表；网格题一律原地改写代替 visited 数组。

### 模板 GR1：网格 DFS——泛洪/沉岛（200, 130）

```java
// 主循环：扫描触发点
for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++)
        if (grid[i][j] == TARGET) {        // 200: 每个 '1' 触发并 count++
            count++;                        // 130: 只从四条边界的 'O' 触发
            dfs(grid, i, j);
        }

private void dfs(char[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length
            || grid[i][j] != TARGET) return;
    grid[i][j] = MARK;                     // 200: '0'（沉掉）; 130: '#'（临时标记）
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
}
// 130 后处理：二次扫描，'O'→'X'（被包围）、'#'→'O'（边界连通，还原）
```

### 模板 GR2：Kahn 算法 BFS 拓扑排序（207, 210, 269）

```java
// 1. 建邻接表 + 入度
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < numNodes; i++) adj.add(new ArrayList<>());
int[] indegree = new int[numNodes];
for (int[] pre : prerequisites) {
    adj.get(pre[1]).add(pre[0]);           // 边方向：prereq -> course
    indegree[pre[0]]++;
}

// 2. 入度为 0 入队
Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < numNodes; i++)
    if (indegree[i] == 0) queue.offer(i);

// 3. BFS：出队即定序，邻居入度减 1，减到 0 入队
int completed = 0;
while (!queue.isEmpty()) {
    int curr = queue.poll();
    completed++;
    // 210: order[idx++] = curr;
    for (int next : adj.get(curr)) {
        if (--indegree[next] == 0) queue.offer(next);
    }
}

// 4. 处理数 == 节点数 ⇔ 无环
// 207: return completed == numCourses;
// 210: return idx == numCourses ? order : new int[0];
// 269 字符版：Map<Character,List<Character>> + Map<Character,Integer> 替代数组；
//     建边 = 比较相邻单词首个不同字符；先判非法前缀 w1.startsWith(w2) && w1 更长 → ""
```

### 模板 GR3：Union-Find（261）

```java
int[] parent = new int[n];
for (int i = 0; i < n; i++) parent[i] = i;

private int find(int[] parent, int x) {
    if (parent[x] != x) {
        parent[x] = find(parent, parent[x]);   // 递归路径压缩
    }
    return parent[x];
}

for (int[] edge : edges) {
    int pu = find(parent, edge[0]);
    int pv = find(parent, edge[1]);
    if (pu == pv) return false;   // 同根再连边 ⇒ 成环
    parent[pu] = pv;
}
// 261 树判定 = 先验 edges.length == n - 1（排除不连通）+ 逐边 union 无环
```

### 模板 GR4：DFS + HashMap 克隆图（133）

```java
private Node dfs(Node node, Map<Node, Node> map) {
    if (map.containsKey(node)) return map.get(node);  // 已克隆直接返回，防环
    Node clone = new Node(node.val);
    map.put(node, clone);                             // 必须先放 map 再递归邻居
    for (Node neighbor : node.neighbors)
        clone.neighbors.add(dfs(neighbor, map));
    return clone;
}
```

### 模板 GR5：HashSet 隐式图/序列起点扩展（128）

```java
Set<Integer> set = new HashSet<>();
for (int num : nums) set.add(num);
int maxLen = 0;
for (int num : set) {
    if (!set.contains(num - 1)) {        // 只从"链头"开始，每个数最多访问两次
        int current = num, len = 1;
        while (set.contains(current + 1)) { current++; len++; }
        maxLen = Math.max(maxLen, len);
    }
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| GR1 网格 DFS | 200（沉岛计数）、130（边界反向标记+翻转） |
| GR2 Kahn 拓扑排序 | 207（判可行）、210（输出序列）、269（字符节点 Map 版） |
| GR3 Union-Find | 261 Graph Valid Tree |
| GR4 DFS+Map 克隆 | 133 Clone Graph |
| GR5 HashSet 序列扩展 | 128 Longest Consecutive Sequence |
| 分层 BFS | 127 Word Ladder（string 包）、126 Word Ladder II（backtracking 包，BFS 建图+回溯） |

---

## 12. Greedy

**识别信号**：局部最优可推出全局最优；"能不能到/最少几步/最少资源"。

### 模板 G1：对撞双指针贪心（11）

```java
int left = 0, right = arr.length - 1, best = 0;
while (left < right) {
    best = Math.max(best, evaluate(arr, left, right));  // 如 min(h[l],h[r]) * (r-l)
    if (arr[left] < arr[right]) left++;   // 贪心：移动受限（较小）的一端
    else                        right--;
}
```

### 模板 G2：单遍累积 + 失败重置起点（134）

```java
int total = 0;   // 全局可行性判据
int tank = 0;    // 从当前候选起点出发的累积量
int start = 0;
for (int i = 0; i < gas.length; i++) {
    int diff = gas[i] - cost[i];
    total += diff;
    tank += diff;
    if (tank < 0) {       // 从 start 到 i 都不可能是起点
        start = i + 1;
        tank = 0;
    }
}
return total >= 0 ? start : -1;
```

### 模板 G3：双向两遍扫描贪心（135）

```java
int[] candies = new int[n];
Arrays.fill(candies, 1);                                  // 基线：人手一个
for (int i = 1; i < n; i++)                               // 左→右：只管左邻约束
    if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;
for (int i = n - 2; i >= 0; i--)                          // 右→左：取 max 兼顾两侧
    if (ratings[i] > ratings[i + 1]) candies[i] = Math.max(candies[i], candies[i + 1] + 1);
```

### 模板 G4：跳跃游戏——维护最远可达/分层（55, 45，在 dp 包）

```java
// 55：维护 farthest
int farthest = 0;
for (int i = 0; i < n; i++) {
    if (i > farthest) return false;
    farthest = Math.max(farthest, i + nums[i]);
}
return true;

// 45：贪心 BFS 分层，curEnd/curFarthest
int jumps = 0, curEnd = 0, curFarthest = 0;
for (int i = 0; i < n - 1; i++) {
    curFarthest = Math.max(curFarthest, i + nums[i]);
    if (i == curEnd) {          // 用尽当前层范围
        jumps++;
        curEnd = curFarthest;
    }
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| G1 对撞贪心 | 11 Container With Most Water |
| G2 失败重置起点 | 134 Gas Station |
| G3 双向两遍扫描 | 135 Candy |
| G4 跳跃/最远可达 | 55、45（dp 包）；122 正差价累加（dp 包） |

---

## 13. Intervals

**识别信号**：区间合并/插入/资源调度。第一步几乎总是 **按起点排序**：`Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]))`。重叠判据：`curr[1] >= next[0]`。

### 模板 I1：排序 + 一次遍历合并（56）

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
List<int[]> merged = new ArrayList<>();
int[] curr = intervals[0];
merged.add(curr);
for (int i = 1; i < intervals.length; i++) {
    int[] next = intervals[i];
    if (curr[1] >= next[0]) {                        // 重叠
        curr[1] = Math.max(curr[1], next[1]);        // 原地扩展（curr 是列表内引用）
    } else {
        curr = next;
        merged.add(curr);
    }
}
return merged.toArray(new int[merged.size()][]);
```

### 模板 I1b：已排序区间的三阶段插入（57）

```java
List<int[]> result = new ArrayList<>();
int i = 0, n = intervals.length;
while (i < n && intervals[i][1] < newInterval[0]) result.add(intervals[i++]);  // 阶段1: 完全在左
while (i < n && intervals[i][0] <= newInterval[1]) {                           // 阶段2: 重叠即吞并
    newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
    newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
    i++;
}
result.add(newInterval);
while (i < n) result.add(intervals[i++]);                                      // 阶段3: 剩余
```

### 模板 I2：排序 + 最小堆统计并发资源（253）

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
PriorityQueue<Integer> heap = new PriorityQueue<>();            // 存各房间最早结束时间
heap.offer(intervals[0][1]);
for (int i = 1; i < intervals.length; i++) {
    if (intervals[i][0] >= heap.peek()) heap.poll();  // 最早结束的已散，复用
    heap.offer(intervals[i][1]);
}
return heap.size();                                    // 堆大小 = 所需资源数
```

### 题目归类

| 模板 | 题目 |
|---|---|
| I1 排序+合并 | 56 Merge Intervals |
| I1b 三阶段插入 | 57 Insert Interval（输入已排序） |
| I2 排序+堆 | 253 Meeting Rooms II |

---

## 14. Math & Geometry

**识别信号**：数位操作、进位模拟、矩阵变换、数论。核心难点通常是**溢出处理**（见下方三板斧）。

### 溢出处理三板斧

```java
// 1. 特判唯一溢出输入
if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
// 2. 先 (long) 再运算
long absDividend = Math.abs((long) dividend);   // 50 同理：long N = n;
// 3. 乘 10 之前与 MAX_VALUE / 10 比较预判（7）
if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
```

### 模板 A：数位拆解 `%10` `/10`（7, 9）

```java
int result = 0;
while (x != 0) {
    int digit = x % 10;          // 负数得负余数，天然处理符号
    x /= 10;
    /* 溢出预判（见三板斧 3） */
    result = result * 10 + digit;
}

// 9 半数反转判回文——免溢出检查
if (x < 0 || (x % 10 == 0 && x != 0)) return false;
int rev = 0;
while (x > rev) {                // 只反转一半
    rev = rev * 10 + x % 10;
    x /= 10;
}
return x == rev || x == rev / 10;   // 偶数位 / 奇数位（去中间位）
```

### 模板 B：carry 进位模拟（66, 43；bit 包 67 同族）

```java
// 双序列逐位相加（67 二进制版；十进制换 %10 /10）
int i = a.length() - 1, j = b.length() - 1, carry = 0;
while (i >= 0 || j >= 0 || carry > 0) {   // 三条件合一，carry 自然处理最高位
    int sum = carry;
    if (i >= 0) sum += a.charAt(i--) - '0';
    if (j >= 0) sum += b.charAt(j--) - '0';
    sb.append(sum % 2);
    carry = sum / 2;
}
return sb.reverse().toString();

// 数组加一——提前返回（66）
for (int i = n - 1; i >= 0; i--) {
    if (digits[i] < 9) { digits[i]++; return digits; }
    digits[i] = 0;
}
int[] result = new int[n + 1];   // 全 9：999 -> 1000
result[0] = 1;

// 竖式乘法（43）：digit(i)*digit(j) 落在 i+j 与 i+j+1 位
int[] result = new int[m + n];
for (int i = m - 1; i >= 0; i--) {
    for (int j = n - 1; j >= 0; j--) {
        int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        int sum = mul + result[i + j + 1];
        result[i + j + 1] = sum % 10;
        result[i + j] += sum / 10;
    }
}
```

### 模板 C：矩阵原地旋转 = 转置 + 行反转（48）

```java
for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++) {       // 只扫上三角
        int t = matrix[i][j]; matrix[i][j] = matrix[j][i]; matrix[j][i] = t;
    }
for (int i = 0; i < n; i++)
    for (int j = 0; j < n / 2; j++) {
        int t = matrix[i][j]; matrix[i][j] = matrix[i][n-1-j]; matrix[i][n-1-j] = t;
    }
```

### 模板 D：迭代快速幂（50）

```java
long N = n;                     // long 承接 -Integer.MIN_VALUE
if (N < 0) { x = 1 / x; N = -N; }
double result = 1.0;
while (N > 0) {
    if ((N & 1) == 1) result *= x;  // 指数二进制当前位
    x *= x;
    N >>= 1;
}
```

### 模板 E：其余数论惯用法

```java
// 迭代 GCD（149，配合最简分数作斜率键，规范符号避免浮点误差）
private int gcd(int a, int b) {
    while (b != 0) { int t = b; b = a % b; a = t; }
    return Math.abs(a);
}

// 埃氏筛（204）：外层到 sqrt(n)，内层从 i*i 起步
boolean[] isPrime = new boolean[n];
for (int i = 2; i < n; i++) isPrime[i] = true;
for (int i = 2; i * i < n; i++)
    if (isPrime[i])
        for (int j = i * i; j < n; j += i)
            isPrime[j] = false;

// 平行数组降序贪心（12 Integer to Roman）
int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
for (int i = 0; i < values.length; i++)
    while (num >= values[i]) { num -= values[i]; sb.append(symbols[i]); }

// 前瞻比较决定加减（13 Roman to Integer）
if (i + 1 < s.length() && cur < getValue(s.charAt(i + 1))) result -= cur;
else result += cur;

// 康托展开——第 k 个排列（60）：k / (n-i)! 定位每一位并从候选表移除
```

### 题目归类

| 惯用法 | 题目 |
|---|---|
| 数位拆解 + 溢出预判 | 7、9 |
| carry 进位模拟 | 66、43、67（bit 包） |
| 转置+行反转 | 48 Rotate Image |
| 快速幂 | 50 Pow(x, n) |
| 康托展开 | 60 Permutation Sequence |
| GCD + 斜率哈希 | 149 Max Points on a Line |
| 埃氏筛 | 204 Count Primes |
| 平行数组贪心 / 查表 | 12、13 |

---

## 15. Bit Manipulation

**识别信号**："出现一次/两次/三次"、二进制位统计、不能用乘除法。

### 惯用法速查

```java
// A. XOR 相消求唯一数（136）：a ^ a = 0, a ^ 0 = a
int result = 0;
for (int num : nums) result ^= num;

// B. ones/twos 双掩码状态机——按位模 3（137，可推广"出现 k 次除一个"）
int ones = 0, twos = 0;
for (int num : nums) {
    ones = (ones ^ num) & ~twos;
    twos = (twos ^ num) & ~ones;
}
return ones;

// C. n & (n-1) 清除最低位 1（191；亦可判 2 的幂）
int count = 0;
while (n != 0) {
    n &= (n - 1);
    count++;
}

// D. 逐位收集/位反转（190）：必须 >>> 无符号右移，否则负数死循环
int result = 0;
for (int i = 0; i < 32; i++) {
    result <<= 1;
    result |= (n & 1);
    n >>>= 1;
}

// E. 倍增左移代替除法（29）
while (absDividend >= absDivisor) {
    long temp = absDivisor;
    int multiple = 1;
    while (absDividend >= (temp << 1)) {
        temp <<= 1;
        multiple <<= 1;
    }
    absDividend -= temp;
    quotient += multiple;
}

// F. XOR 判异号（29）
boolean negative = (dividend < 0) ^ (divisor < 0);

// G. XOR 消对找缺失数（268，binarysearch 包）
int result = n;
for (int i = 0; i < n; i++) result ^= i ^ nums[i];

// H. 格雷码公式（89，backtracking 包）
gray = i ^ (i >> 1);
```

> `>>` vs `>>>`：快速幂（50）中指数已保证非负可用 `>>`；位反转（190）处理任意 int 必须 `>>>`。

### 题目归类

| 惯用法 | 题目 |
|---|---|
| A XOR 相消 | 136；268（binarysearch 包） |
| B 双掩码状态机 | 137 |
| C `n & (n-1)` | 191 |
| D 逐位收集 + `>>>` | 190 |
| E/F/G 无乘除除法 | 29 |
| carry 加法模拟 | 67（见 §14 模板 B） |

---

## 16. Trie

**识别信号**：前缀查询、批量字符串匹配（配合网格 DFS 见 212）。

### TrieNode 结构 + 增查模板（208, 211 完全一致）

```java
static class TrieNode {
    TrieNode[] children = new TrieNode[26];   // 小写字母数组映射，c - 'a' 定位
    boolean isEnd;                            // 单词结束标记
}

private TrieNode root;
public Trie() { root = new TrieNode(); }

// 插入（208 insert 与 211 addWord 逐字相同）
public void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        int idx = c - 'a';
        if (node.children[idx] == null) node.children[idx] = new TrieNode();
        node = node.children[idx];
    }
    node.isEnd = true;
}

// 精确/前缀查找共用一个 walk 辅助方法（208）
private TrieNode searchPrefix(String prefix) {
    TrieNode node = root;
    for (char c : prefix.toCharArray()) {
        int idx = c - 'a';
        if (node.children[idx] == null) return null;
        node = node.children[idx];
    }
    return node;
}
public boolean search(String word)    { TrieNode n = searchPrefix(word); return n != null && n.isEnd; }
public boolean startsWith(String pre) { return searchPrefix(pre) != null; }

// 通配符 '.' DFS 查找（211）：枚举 26 个孩子
private boolean dfs(String word, int index, TrieNode node) {
    if (index == word.length()) return node.isEnd;
    char c = word.charAt(index);
    if (c == '.') {
        for (int i = 0; i < 26; i++)
            if (node.children[i] != null && dfs(word, index + 1, node.children[i]))
                return true;
        return false;
    }
    int idx = c - 'a';
    return node.children[idx] != null && dfs(word, index + 1, node.children[idx]);
}
```

### 题目归类

| 模板 | 题目 |
|---|---|
| TrieNode + 迭代 insert/walk | 208 Implement Trie |
| + 通配符 DFS | 211 Add and Search Words |
| + 网格 DFS（node.word 收集后置 null 防重） | 212 Word Search II（backtracking 包） |
| 非 Trie（横向扫描截短前缀） | 14 Longest Common Prefix |

---

## 17. String

**识别信号**：解析/校验 → 索引推进或状态机；统计/映射 → 频次 map；构造输出 → StringBuilder 分段。

### 模板 A：索引/状态驱动的单遍解析（8, 58, 65, 38）

```java
// 变体1: 索引推进 + 分段 while（8 atoi / 58）
int i = 0, n = s.length();
while (i < n && s.charAt(i) == ' ') i++;          // 阶段1: 跳过
// ... 阶段2: 读符号 / 定位单词
while (i < n && Character.isDigit(s.charAt(i))) { // 阶段3: 逐字符消费
    result = result * 10 + (s.charAt(i) - '0');   // 8 中用 long result + 钳位
    i++;
}

// 变体2: boolean 标志状态机（65 Valid Number）
boolean seenDigit = false, seenDot = false, seenExponent = false;
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    if (Character.isDigit(c)) { seenDigit = true; }
    else if (c == '.') { if (seenDot || seenExponent) return false; seenDot = true; }
    // ... e/E、+/- 各自的合法性条件
    else { return false; }
}
return seenDigit;
```

### 模板 B：哈希表统计与映射（242, 205, 49）

```java
// 变体1: 频次加减（242 Valid Anagram）
Map<Character, Integer> map = new HashMap<>();
for (char c : s.toCharArray()) {
    map.put(c, map.getOrDefault(c, 0) + 1);
}
for (char c : t.toCharArray()) {
    int count = map.getOrDefault(c, 0);
    if (count == 0) return false;
    map.put(c, count - 1);
}

// 变体2: 规范化 key 分组（49 Group Anagrams）
Map<String, List<String>> map = new HashMap<>();
for (String s : strs) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    String key = new String(chars);
    map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
}

// 变体3: 双向映射保证双射（205 Isomorphic）：mapST + mapTS 两个 map
```

### 模板 C：StringBuilder 逐段构造/模拟（6, 38, 68）

```java
StringBuilder sb = new StringBuilder();
// 外层控制"段"（一行 / 一个 term），内层逐字符或逐词 append
for (/* 每一段 */) {
    // 计算本段内容（方向翻转 / 游程计数 / 空格分配）
    sb.append(/* 段内容 */);
}
return sb.toString();
```

### 题目归类

| 模板 | 题目 |
|---|---|
| A 单遍解析/状态机 | 8（多阶段+钳位）、58（逆向双 while）、65（标志位状态机）、38（游程编码内层） |
| B 哈希统计/映射 | 242、205（双向映射）、49（排序 key 分组） |
| C StringBuilder 构造 | 6（`StringBuilder[]` 分桶+方向标志）、38、68（贪心装行+空格分配） |
| 分层 BFS | 127 Word Ladder（26 字母枚举变换，标准 `size = queue.size()` 层序模板） |

---

## 附：跨模式通用惯用法

| 惯用法 | 出处 |
|---|---|
| `!set.add(x)` 判重 | 217、219、36 |
| `map.getOrDefault(k, 0) + 1` 频次累加 | 242、30、76、149 |
| `map.computeIfAbsent(k, x -> new ArrayList<>())` 分组 | 49 |
| `int mid = left + (right - left) / 2` 防溢出 | binarysearch 全包 |
| `while (i >= 0 \|\| j >= 0 \|\| carry > 0)` 进位循环 | 2、67、43、66 |
| 排序 + 同层跳过去重 `nums[i] == nums[i-1]` | 15、18、40、47、90 |
| 原地改写代替 visited（'#' / '0'） | 79、212、200、130 |
| 哨兵简化边界：dummy（链表）、`i == n` 补 0（单调栈）、`dp[0]` 空串基例 | 全仓库 |
| 空间压缩：二维 → 一维滚动 → 常数变量 | dp 包全线 |
