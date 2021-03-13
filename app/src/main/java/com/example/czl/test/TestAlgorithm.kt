package com.example.czl.test

import java.lang.StringBuilder
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import kotlin.collections.HashMap
import kotlin.math.max


class TestAlgorithm {


    /**实现基本计算器*/
    fun test27() {
        val express = "1+2*3-5+4/2*3"
        val numStack = Stack<String>()
        val optStack = Stack<Char>()
        var index = 0
        var numStr = ""
        while (index < express.length) {
            val char = express.get(index)
            if (char == ' ') {
                index++
                continue
            }
            if (checkIsNum(char)) {
                numStr += char.toString()
                index++
                if (index == express.length) {//当字符串的结尾是数字的时候
                    numStack.push(numStr)
                }
                continue
            }
            if (numStr.isNotEmpty()) {
                numStack.push(numStr)
                numStr = ""
            }
            if (checkIsBrace(char)) {
                optStack.push(char)
                index++
                continue
            }
            if (checkIsBrace2(char)) {
                var brace = optStack.pop()
                while (!checkIsBrace(brace)) {
                    numStack.push(calculate1(numStack.pop(), numStack.pop(), brace))
                    if (optStack.isEmpty()) {
                        break
                    } else {
                        brace = optStack.pop()
                    }
                }
                index++
                continue
            }
            if (optStack.isNotEmpty()) {
                while (optStack.isNotEmpty()) {
                    val topOpt = optStack.pop()
                    if (checkOptPriority(topOpt, char)) {
                        var num1 = "0"
                        var num2 = "0"
                        if (numStack.isNotEmpty()) {
                            num1 = numStack.pop()
                        }
                        if (numStack.isNotEmpty()) {
                            num2 = numStack.pop()
                        }
                        val result = calculate1(num1, num2, topOpt)
                        numStack.push(result)
                    } else {
                        optStack.push(topOpt)
                        optStack.push(char)
                        break
                    }
                }
            } else {
                optStack.push(char)
            }
            index++
        }
        while (numStack.isNotEmpty() && optStack.isNotEmpty()) {
            numStack.push(calculate1(numStack.pop(), numStack.pop(), optStack.pop()))
        }
        while (numStack.isNotEmpty()) {
            println("$express 的计算结果：" + numStack.pop())
        }
    }

    private fun checkIsBrace(char: Char): Boolean {
        return char == '('
    }

    private fun checkIsBrace2(char: Char): Boolean {
        return char == ')'
    }

    private fun calculate1(num1: String, num2: String, opt: Char): String {
        val x = num2.toInt()
        val y = num1.toInt()
        val result = when (opt) {
            '+' -> x + y
            '-' -> x - y
            '*' -> x * y
            '/' -> x / y
            else ->
                0
        }
        return result.toString()
    }

    private fun checkOptPriority(start: Char, end: Char): Boolean {
        if (start == '*' || start == '/') {
            return true
        }
        if ((start == '+' || start == '-') && (end == '+' || end == '-')) {
            return true
        }
        return false
    }

    private fun checkIsNum(char: Char): Boolean {
        return char.toInt() >= '0'.toInt() && char.toInt() <= '9'.toInt()
    }

    private data class SingleNode(var value: Int, var next: SingleNode?) {

    }

    /**判断单链表是否包含环，如果有环的话获取环的入口节点*/
    fun test26() {
        /*val node6: SingleNode = SingleNode(6, null)
        val node5: SingleNode = SingleNode(5, node6)
        val node4: SingleNode = SingleNode(4, node5)
        val node3: SingleNode = SingleNode(3, node4)
        val node2: SingleNode = SingleNode(2, node3)
        val node1: SingleNode = SingleNode(1, node2)
        node6.next = node3*/

        val node4: SingleNode = SingleNode(-4, null)
        val node3: SingleNode = SingleNode(0, node4)
        val node2: SingleNode = SingleNode(2, node3)
        val node1: SingleNode = SingleNode(3, node2)
        node4.next = node2
        findRingList(node1)
        findRingList2(node1)
    }


    /**使用快慢指针的方式寻找单向链表(singly linked list)的开始入口*/
    private fun findRingList2(head: SingleNode?) {
        if (head == null) {
            println("链表为空，无法构成环1")
            return
        }
        var fast = head
        var slow = head
        while (true) {
            if (fast == null) {
                println("链表为空，无法构成环2")
                return
            }
            fast = fast.next
            if (fast == null) {
                println("链表为空，无法构成环3")
                return
            }
            fast = fast.next
            if (slow == null) {
                println("链表为空，无法构成环4")
                return
            }
            slow = slow.next
            if (fast == slow) {//快慢节点第一次相遇
                //找到第一次相遇的位置,slow中保存了相遇位置的节点
                break
            }
        }

        fast = head
        while (true) {
            if (fast == null) {
                println("链表为空，无法构成环5")
                return
            }
            fast = fast.next
            if (slow == null) {
                println("链表为空，无法构成环6")
                return
            }
            slow = slow.next
            if (fast == slow) {//快慢节点第二次相遇
                break
            }
        }
        println("链表中有环，环的入口为 ${fast?.value}")
    }


    /**
     * 寻找单指针链表中环的入口节点，没有节点的话返回null
     * 单指针链表的特点：最多有一个环
     *
     * 算法的核心思想：外层循环遍历链表每一个节点nodeA，内层循环以头节点root开始nodeA节点结束，遍历二者之间的节点node是否跟nodeA形成闭环
     * 即nodeA.next==node.
     * 内层循环结束的条件是nodeA.next==node或nodeA==node;外层循环结束的条件是nodeA=null(如果链表没有形成的环，最后一个节点的子节点肯定为空)
     *
     * */
    private fun findRingList(root: SingleNode?) {
        var ringFlag = false
        var ringStartNode = root
        var currentNode = root?.next
        outloop@ while (currentNode != null) {
            var startNode = root
            while (startNode != null) {
                startNode = startNode.next
                if (currentNode.next == startNode) {
                    ringStartNode = startNode
                    ringFlag = true
                    break@outloop
                }
                if (startNode == currentNode) {
                    break
                }
            }
            currentNode = currentNode.next
        }
        if (!ringFlag) {
            println("单链表不包含闭环")
        } else {
            if (ringStartNode == null) {
                println("单链表包含闭环，起始节点：null")
            } else {
                println("单链表包含闭环，起始节点：${ringStartNode.value}")
            }
        }
    }

    /**将二叉树展开为单链表(使用原来的TreeNode)*/
    fun test25() {
        val rooth = TreeNote("h", 8, null, null)
        val rooti = TreeNote("i", 9, null, null)
        val rootd = TreeNote("d", 4, rooth, rooti)
        val roote = TreeNote("e", 5, null, null)
        val rootb = TreeNote("b", 2, rootd, roote)
        val rootf = TreeNote("f", 6, null, null)
        val rootg = TreeNote("g", 7, null, null)
        val rootc = TreeNote("c", 3, rootf, rootg)
        val roota = TreeNote("a", 1, rootb, rootc)
        println("将二叉树转换为单链表(递归方式)")
        changeTree2List(roota)
        var node: TreeNote? = roota
        while (node != null) {
            print("${node.value}")
            node = node.right
        }
        println()
        println("将二叉树转换为单链表(先序遍历，再输出)")
        changeTree2List_2(roota)
        println()

    }

    /**将二叉树转换为单链表(使用原来的节点)-递归方式*/
    fun changeTree2List(treeNote: TreeNote?) {
        if (treeNote != null) {
            val leftChild = treeNote.left
            val rightChild = treeNote.right
            if (leftChild != null) {
                treeNote.right = treeNote.left
                treeNote.left = null
                //转移右节点到原来左节点的最右节点
                var node: TreeNote = leftChild
                while (node.right != null) {
                    node = node.right!!
                }
                node.right = rightChild
            }
            changeTree2List(treeNote.right)
        }
    }

    /**使用先序遍历加重新构建链表的方式（非递归方式）*/
    private fun changeTree2List_2(treeNode: TreeNote?) {
        if (treeNode != null) {
            val stack = Stack<TreeNote>()
            val list = MutableList<TreeNote>(0) { TreeNote("", 0, null, null) }
            stack.push(treeNode)
            while (!stack.isEmpty()) {
                val node = stack.pop()
                list.add(node)
                if (node.right != null) {
                    stack.push(node.right)
                }
                if (node.left != null) {
                    stack.push(node.left)
                }
            }
            for (i in list.indices) {
                if (i == list.size - 1) {
                    list.get(i).right = null
                } else {
                    list.get(i).right = list.get(i + 1)
                }
                list.get(i).left = null
            }
            var root: TreeNote? = list.get(0)
            while (root != null) {
                print("${root.value}")
                root = root.right
            }
        }
    }

    /**打印两个字符串中的最长公共子串*/
    fun test24() {
//        val s1 = "abcdefgh"
//        val s2 = "abciudkfhajd"
//        val s1 = "abcdefabc"
//        val s2 = "abcabcghtabcdefghi"
//        val s1="aaaaaaaa"
//        val s2="aaaaaaaaaaa"
        val s1 = "iuyt"
        val s2 = "abcdefouyit"
        var tempS1 = ""
        var tempS2 = ""
        var counter = 0
        var maxStartIndex = 0
        var maxEndIndex = 0
        val maxLengthList = MutableList(0) { "" }
        for (i in s1.indices) {
            counter = i
            for (j in s2.indices) {
                tempS1 += s1.get(counter)
                tempS2 += s2.get(j)
                if (tempS1.equals(tempS2)) {//如果相等
                    if ((maxEndIndex - maxStartIndex) > (counter - i)) {
                        //不改变最大值
                    } else if ((maxEndIndex - maxStartIndex) == (counter - i)) {
                        //刚刚找到的字符串的长度跟之前已找到字符串长度相同的话，将字符串加入到集合中
                        if (!maxLengthList.contains(tempS1)) {//过滤掉重复的子串
                            maxLengthList.add(tempS1)
                        }
                    } else {//找的新的最长字符串
                        maxStartIndex = i//改变最长公共子串索引
                        maxEndIndex = counter//改变最长公共子串索引
                        maxLengthList.clear()//清空集合，保存最新字符串
                        maxLengthList.add(tempS1)
                    }
                    counter++//计数器加1，进行下次比较
                    if (counter >= s1.length) {//如果s1子串已经到末尾的话，结束这次循环，进行下次外循环
                        break//结束内层循环
                    }
                } else {//如果不相等
                    println(s1.substring(i, counter))//打印上一次比较时相等的部分
                    counter = i
                    tempS1 = ""
                    tempS2 = ""//重置，开始比较
                    if (s1[i] == s2[j]) {//这段代码作用：当比较abcdef和abcabc时，当比较到d和a时(abcd-abca),下次循环开始的比较是a和b，忽略了上次比较不同的最后一个元素a(abcabc中的第3个)
                        tempS1 += s1[i].toString()
                        tempS2 += s2[j].toString()
                        counter++
                    }
                }
            }
        }
        println("$s1 和$s2 最长公共子串为")
        println(maxLengthList)
    }

    data class TreeNote(
        val value: String,//为了输出字符串(跟网上的题达到重合)
        val num: Int,//值，为了计算层平均数
        var left: TreeNote?,
        var right: TreeNote?
    ) {

    }

    fun test23() {
        val rooth = TreeNote("h", 8, null, null)
        val rooti = TreeNote("i", 9, null, null)
        val rootd = TreeNote("d", 4, rooth, rooti)
        val roote = TreeNote("e", 5, null, null)
        val rootb = TreeNote("b", 2, rootd, roote)
        val rootf = TreeNote("f", 6, null, null)
        val rootg = TreeNote("g", 7, null, null)
        val rootc = TreeNote("c", 3, rootf, rootg)
        val roota = TreeNote("a", 1, rootb, rootc)

        println("前序遍历")
        printPreNote(roota)
        println()
        println("中序遍历")
        printMiddleNote(roota)
        println()
        println("后序遍历")
        printPostNote(roota)
        println()
        println("按照层次遍历")
        print(roota.value)
        val result = MutableList(0) {
            MutableList(0) {
                ""
            }
        }
        printLevelNote(roota, result, 1)
        println(result.toString())
        println("先序遍历二叉树(非递归方式)")
        preOrderNote(roota)
        println()
        println("中序遍历二叉树(非递归方式)")
        middleOrderNote(roota)
        println()
        println("后序遍历二叉树(非递归方式)")
        postOrderNote(roota)
        println()
        println("层序遍历二叉树(非递归方式)")
        levelOrderNode(roota)
        println()
    }

    /**层序遍历二叉树(非递归方式)，比递归简单好多啊*/
    private fun levelOrderNode(head: TreeNote?) {
        if (head != null) {
            val result = MutableList<MutableList<String>>(0) {
                MutableList(0) {
                    ""
                }
            }
            val rightList = MutableList<String>(0) { "" }
            val levelAverageList = MutableList<Int>(0) { 0 }
            val queue = ArrayBlockingQueue<TreeNote>(16)
            queue.add(head)
            while (!queue.isEmpty()) {
                val size = queue.size//当前level中节点个数
                val list = MutableList(0) { "" }
                var sum: Int = 0
                for (i in 0 until size) {
                    val node = queue.poll()
                    list.add(node.value)
                    if (i == (size - 1)) {
                        rightList.add(node.value)//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
                    }
                    sum += node.num
                    if (node.left != null) {
                        queue.add(node.left)
                    }
                    if (node.right != null) {
                        queue.add(node.right)
                    }
                }
                result.add(list)
                levelAverageList.add(sum / size)//求每一层节点的平均值
            }
            //从根节点开始按层输出
            println("从根节点开始输出")
            for (i in result.indices) {
                println(result.get(i).toString())
            }
            //从叶子节点开始按层输出
            println("从叶子节点开始输出")
            result.reverse()
            for (i in result.indices) {
                println(result.get(i).toString())
            }
            println("从根节点开始只输出每一层最右边的节点")
            println(rightList)
            println("二叉树每一层节点的平均值")
            println(levelAverageList)
        }
    }

    private fun test23_1(head: TreeNote?) {
        //先序遍历(非递归)
        if (head != null) {
            val stack1 = Stack<TreeNote>()
            stack1.push(head)
            while (!stack1.isEmpty()) {
                var note = stack1.pop()
                print("${note.value},")
                if (note.right != null) {
                    stack1.push(note.right)
                }
                if (note.left != null) {
                    stack1.push(note.left)
                }
            }
        }
        //中序遍历二叉树(非递归)
        if (head != null) {
            val stack2 = Stack<TreeNote>()
            var node = head
            while (!stack2.isEmpty() || node != null) {
                if (node != null) {
                    stack2.push(node)
                    node = node.left
                } else {
                    node = stack2.pop()
                    print("${node.value},")
                    node = node.right
                }
            }
        }
        //后序遍历二叉树(非递归)
        if (head != null) {
            val stack3 = Stack<TreeNote>()
            val stack4 = Stack<TreeNote>()
            while (!stack3.isEmpty()) {
                val note = stack3.pop()
                stack4.push(note)
                if (note.left != null) {
                    stack3.push(note.left)
                }
                if (note.right != null) {
                    stack3.push(note.right)
                }
            }
            while (!stack4.isEmpty()) {
                print("${stack4.pop().value},")
            }
        }
    }

    //使用迭代法(非递归)先序遍历
    private fun preOrderNote(head: TreeNote?) {
        if (head != null) {
            val stack = Stack<TreeNote>()
            stack.add(head)

            while (!stack.isEmpty()) {
                val node = stack.pop()
                print("${node.value},")
                if (node.right != null) {
                    stack.push(node.right)
                }
                if (node.left != null) {
                    stack.push(node.left)
                }
            }
        }
    }

    /**中序遍历二叉树*/
    private fun middleOrderNote(head: TreeNote?) {
        if (head != null) {
            val stack = Stack<TreeNote>()
            var note = head
            while (!stack.isEmpty() || note != null) {
                if (note != null) {
                    stack.push(note)
                    note = note.left
                } else {
                    note = stack.pop()
                    print("${note.value},")
                    note = note.right
                }
            }
        }
    }

    /*后序遍历二叉树*/
    private fun postOrderNote(head: TreeNote?) {
        if (head != null) {
            val stack1 = Stack<TreeNote>()
            val stack2 = Stack<TreeNote>()
            stack1.push(head)
            var note: TreeNote
            while (!stack1.isEmpty()) {
                note = stack1.pop()
                stack2.push(note)
                if (note.left != null) {
                    stack1.push(note.left)
                }
                if (note.right != null) {
                    stack1.push(note.right)
                }
            }
            while (!stack2.isEmpty()) {
                print("${stack2.pop().value},")
            }
        }
    }


    private fun printLevelNote(
        treeNote: TreeNote,
        ans: MutableList<MutableList<String>>,
        level: Int
    ) {
        if (ans.size < level) {
            ans.add(MutableList(0) {
                ""
            })
        }
        ans.get(level - 1).add(treeNote.value ?: "")
        if (treeNote.left != null) {
            printLevelNote(treeNote.left!!, ans, level + 1)
        }
        if (treeNote.right != null) {
            printLevelNote(treeNote.right!!, ans, level + 1)
        }
    }

    private fun printMiddleNote(treeNote: TreeNote?) {
        if (treeNote == null) {
            return
        }
        printMiddleNote(treeNote.left)
        print("${treeNote.value}")
        printMiddleNote(treeNote.right)
    }

    private fun printPostNote(treeNote: TreeNote?) {
        if (treeNote == null) {
            return
        }
        printPostNote(treeNote.left)
        printPostNote(treeNote.right)
        print("${treeNote.value}")
    }

    private fun printPreNote(treeNote: TreeNote?) {
        if (treeNote == null) {
            return
        }
        print("${treeNote.value}")
        printPreNote(treeNote.left)
        printPreNote(treeNote.right)
    }

    /**图形旋转（矩阵的旋转）*/
    fun test22() {
        val matrix = arrayOf(
            arrayOf(5, 1, 9, 11),
            arrayOf(2, 4, 8, 10),
            arrayOf(13, 3, 6, 7),
            arrayOf(15, 14, 12, 16)
        )

        val row = matrix.size
        val column = matrix[0].size
        println("矩阵变换前")
        for (i in 0 until row) {
            matrix[i].toPrint()
            println()
        }
        //首先上下对折
        for (i in 0 until column) {
            for (j in 0 until row / 2) {
                val temp = matrix[j][i]
                matrix[j][i] = matrix[row - j - 1][i]
                matrix[row - j - 1][i] = temp
            }
        }
        //然后对角线对折
        var begin = 0
        for (i in 0 until row) {
            for (j in begin until column) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
            begin++
        }
        println("矩阵旋转90度后")
        for (i in 0 until row) {
            matrix[i].toPrint()
            println()
        }
    }

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    fun test21() {
        val matrix2 = arrayOf(
            arrayOf(1, 4, 7, 11, 15),
            arrayOf(2, 5, 8, 12, 19),
            arrayOf(3, 6, 9, 16, 22),
            arrayOf(10, 13, 14, 17, 24),
            arrayOf(18, 21, 2326, 30)
        )
        val m = matrix2.size//行
        val n = matrix2[0].size//列
//        val target = 5
//        val target = 15
        val target = 20
        var i = m - 1
        var j = 0
        var findFlag = false
        while (true) {
            if (i < 0 || j >= n) {
                break
            }
            if (matrix2[i][j] < target) {
                j++
            } else if (matrix2[i][j] > target) {
                i--
            } else {
                findFlag = true
                break
            }
        }
        if (findFlag) {
            println("矩阵中包含 $target")
        } else {
            println("矩阵中不包含 $target")
        }
    }

    fun test20() {
        val intArray = arrayOf(2, 3, 5, 6, 9, 12, 7, 10, 14)
        val target = 20
        print("数组中元素相加和为$target 所有组合(重复)")
        intArray.toPrint()
        var startTime = System.currentTimeMillis()
        calculateSum(0, intArray, target, "")
        println("第一种方法耗时：${System.currentTimeMillis() - startTime}")
        println("数组中元素相加和为$target 所有组合(不重复)")
        startTime = System.currentTimeMillis()
        calculateSum2(0, 0, intArray, target, "")
        println("第二种方法耗时：${System.currentTimeMillis() - startTime}")
        println("数组中元素相加和为$target 所有组合(不重复+剪枝优化)")
        val intArraySort = intArray.clone()
        startTime = System.currentTimeMillis()
        intArraySort.sort()
        calculateSum3(0, 0, intArraySort, target, "")
        println("第三种方法耗时：${System.currentTimeMillis() - startTime}")
    }

    /**对第二种方法优化(剪枝：对给定数组排序，然后遍历的时候添加逻辑处理)*/
    private fun calculateSum3(
        tempSum: Int,
        begin: Int,
        intArray: Array<Int>,
        target: Int,
        s: String
    ) {
        for (i in begin until intArray.size) {
            val tempSum2 = tempSum + intArray[i]
            if (tempSum2 == target) {
                println(s + intArray[i])
                return
            } else if (tempSum2 < target) {
                calculateSum2(tempSum2, i, intArray, target, s + intArray[i])
            } else {
                break
            }
        }
    }

    /**去除重复元素(元素中的每一个值不考虑顺序)*/
    private fun calculateSum2(
        tempSum: Int,
        begin: Int,
        intArray: Array<Int>,
        target: Int,
        s: String
    ) {
        for (i in begin until intArray.size) {
            val tempSum2 = tempSum + intArray[i]
            if (tempSum2 == target) {
                println(s + intArray[i])
                return
            } else if (tempSum2 < target) {
                calculateSum2(tempSum2, i, intArray, target, s + intArray[i])
            }
        }
    }

    //给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合
    //所有数字（包括 target）都是正整数。
    //解集不能包含重复的组合。
    //该方法搜索出的结果中有重复元素(在不考虑元素中每一个值的顺序情况)
    private fun calculateSum(tempSum: Int, intArray: Array<Int>, target: Int, s: String) {
        for (i in intArray.indices) {
            val tempSum2 = tempSum + intArray[i]
            if (tempSum2 == target) {//当求得的值等于sum时，不再寻找有效元素，因为再寻找的元素等于当前元素
                println(s + intArray[i])
                return
            } else if (tempSum2 < target) {
                calculateSum(tempSum2, intArray, target, s + intArray[i])
            } else {
                //当求得的值大于sum时，遍历下一个元素进行验证
            }
        }
    }

    /**定义数组的打印扩展方法*/
    private fun <T> Array<T>.toPrint() {
        if (isEmpty()) {
            println("数组是空的")
            return
        }
        print("[")
        var divider = ""
        for (i in indices) {
            val t = this[i]
            print("$divider${t.toString()}")
            divider = ","
        }
        print("]")
        println()
    }

    /**对数组进行全排列*/
    fun test19() {
//        val intArray = arrayOf(1, 2, 3)
        val intArray = arrayOf(1, 2, 3, 4, 5)
        println("数组的全队列")
        intArray.toPrint()
        checkInt(intArray, "")
    }

    private fun checkInt(array: Array<Int>, s: String) {
        if (s.length == array.size) {
            println(s)
        }
        for (i in array.indices) {
            if (array[i] != -1) {
                val temp = array[i]
                val copyArray = array.clone()
                copyArray[i] = -1
                checkInt(copyArray, s + temp)//剔除array中的第i个元素，构成新的array传递给checkInt方法
            }
        }
    }

    val keyWords1 = arrayOf("leet", "code")
    val keyWords2 = arrayOf("apple", "pen")
    val keyWords3 = arrayOf("cats", "dog", "sand", "and", "cat")
    val map1 = HashMap<String, Boolean>()
    var flag = false
    fun test18() {
//        test18_1()
        test18_2()
    }

    /*记忆回溯法(递归法加记忆法)*/
    private fun test18_2() {
        val inputString1 = "leetcode"
        val inputString2 = "applepenapple"
        val inputString3 = "catsandog"
        val result1 = helper(inputString1, keyWords1)
        if (result1) {
            println("$inputString1 是合法字符串")
        } else {
            println("$inputString1 不是合法字符串")
        }
        val result2 = helper(inputString2, keyWords2)
        if (result2) {
            println("$inputString2 是合法字符串")
        } else {
            println("$inputString2 不是合法字符串")
        }
        val result3 = helper(inputString3, keyWords3)
        if (result3) {
            println("$inputString3 是合法字符串")
        } else {
            println("$inputString3 不是合法字符串")
        }
    }

    private fun helper(s: String, keyWords: Array<String>): Boolean {
        if (s.isEmpty()) {
            return true
        }
        if (map1.containsKey(s)) {
            return map1.get(s) ?: false
        }
        var ans = false
        for ((index, value) in keyWords.withIndex()) {
            if (s.startsWith(value)) {
                if (helper(s.substring(value.length, s.length), keyWords)) {
                    ans = true
                    break
                }
            }
        }
        map1.put(s, ans)
        return ans
    }

    /**自己实现的递归方法*/
    fun test18_1() {
        val inputString1 = "leetcode"
        val inputString2 = "applepenapple"
        val inputString3 = "catsandog"
        checkWords(inputString1, keyWords1)
        if (flag) {
            println("$inputString1 是合法的")
        } else {
            println("$inputString1 是不合法的")
        }
        flag = false
        checkWords(inputString2, keyWords2)
        if (flag) {
            println("$inputString2 是合法的")
        } else {
            println("$inputString2 是不合法的")
        }
        flag = false
        checkWords(inputString3, keyWords3)
        if (flag) {
            println("$inputString3 是合法的")
        } else {
            println("$inputString3 是不合法的")
        }
    }

    private fun checkWords(inputString: String, keyWords: Array<String>) {
        for (i in 0..inputString.length) {
            if (flag) {
                break
            }
            val sub = inputString.substring(0, i)
            if (keyWords.contains(sub)) {
                if (i < inputString.length) {
                    checkWords(inputString.substring(i, inputString.length), keyWords)
                } else {
                    flag = true
                }
            }
        }
    }

    /**
     * 生成n对不重复的小括号
     * */
    private val resultList: MutableList<String> = MutableList(0) {
        ""
    }

    fun test17() {
        val n = 5
        dfs(n, n, "")
        for ((index, value) in resultList.withIndex()) {
            print("$value,")
        }
        println()
    }

    private fun dfs(left: Int, right: Int, resultStr: String) {
        if (left == 0 && right == 0) {
            resultList.add(resultStr)
            return
        }
        if (left > 0) {
            dfs(left - 1, right, resultStr + "(")
        }
        if (right > left) {
            dfs(left, right - 1, resultStr + ")")
        }
    }

    /**
     * 计算101-200之间的素数(质数)
     *
     * */
    fun test1() {
        var counter = 0
        for (i in 101..200) {//i的值从101-200，包含101也包含200
            var flag: Boolean = true
            for (j in 2..Math.sqrt(i.toDouble()).toInt()) {
                if (i % j == 0) {
                    flag = false
                    break
                }
            }
            if (flag) {
                counter++
                println(i)
            }
        }
        println("101-200中所有素数个数：$counter")
    }

    /**
     * 打印水仙花数，水仙花数：一个三位数其各位数字的立方和等于该数本身
     * */
    fun test2() {
        var counter = 0
        for (i in 101..999) {
            val num1 = i / 100//整除
            val num2 = i % 100 / 10//先求余数，再整除
            val num3 = i % 10//直接求余数
            val result = Math.pow(num1.toDouble(), 3.0) + Math.pow(
                num2.toDouble(),
                3.0
            ) + Math.pow(num3.toDouble(), 3.0)
            if (result.toInt() == i) {
                counter++
                println(i)
            }
        }
        println("101-999水仙花数个数：$counter")
    }

    /**
     * 分解整数的质因数
     * */
    fun test3() {
        println("-------------打印整数的质因数-------------")
//        printFactor(90)
//        printFactor(99)
        printFactor(120)
    }

    private fun printFactor(param: Int) {
        for (i in 2..param / 2) {
            if (param % i == 0) {
                println("i=$i,param=$param")//打印因数
                printFactor(param / i)
                return
            }
        }
        println("i=$param,param=$param")
    }


    /*使用when语句打印成绩区间*/
    fun test4() {
        println("--------------打印成绩区间----------------")
        val gradeArray = arrayOf(100, 90, 80, 69, 59, 70, 99, 40, 30)
        for (i in gradeArray) {
            val result: String = when (i) {
                in 90..100 -> "A"
                in 70..89 -> "B"
                in 60..69 -> "C"
                else -> "D"
            }
            println("gradle：$i - $result")
        }
    }

    /**求两个整数的最大公约数和最小公倍数
     * 最小公倍数=整数1*整数2/最大公约数
     * */
    fun test5() {
        /*val num1 = 12
        val num2 = 30*/
        val num1 = 24
        val num2 = 60
        val maxNum = if (num1 > num2) num1 else num2
        val minNum = if (num1 > num2) num2 else num1
        var result1 = 0
        for (i in 2..minNum / 2) {
            if ((maxNum % i == 0) && (minNum % i == 0)) {
                result1 = i
            }
        }
        val result2 = maxNum * minNum / result1
        println("最大公约数：$result1，最小公倍数：$result2")
    }

    /**统计字符串中的字符的个数*/
    fun test6() {
        val inputString = "hello 123 %&* world!!! "
        var letterCount = 0
        var numberCount = 0
        var spaceCount = 0
        var specialCharCount = 0
        val charArray = inputString.toCharArray()
        for (char in charArray) {
            if (char.isLetter()) {
                letterCount++
            } else if (char.isDigit()) {
                numberCount++
            } else if (char.isWhitespace()) {
                spaceCount++
            } else {
                specialCharCount++
            }
        }
        println("letterCount=$letterCount,numberCount=$numberCount,spaceCount=$spaceCount,specialCharCount=$specialCharCount")
    }


    /**
     * 一球从100米高度自由落下，每次落地后反跳回原高度的一半；
     * 再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高
     * */
    fun test7() {
        val startNum = 100.0f//球初始高度
        var tempValue = startNum
        var reboundHeightN = 0.0f//某一次球落地后反弹的高度
        var sum = 0.0f//球落下+弹起经过的总距离
        var counter = 0//球下落次数
        while (true) {
            counter++
            sum += (tempValue + tempValue / 2.0f)//球每次落地后弹起高度为原来的1/2
            tempValue /= 2//球下次下落时候的高度(也是上次落地后弹起的高度)
            reboundHeightN = tempValue//球下次下落时候的高度(也是上次落地后弹起的高度)
            if (counter == 10) {
                break
            }
        }
        println("球初始高度：$startNum,第$counter 次下落后经过的距离总和：${sum - reboundHeightN},弹起高度：$reboundHeightN")
    }

    /**
     * 斐波那契数列：fn=f(n1-)+f(n-2)
    有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少
     */
    fun test8() {
        var f1: Long = 1
        var f2: Long = 1
        println()
        print("$f1,$f2,")
        var tempNum: Long = 0
        for (i in 3..10) {
            tempNum = f2
            f2 = f1 + f2
            f1 = tempNum
            print("$f2,")
        }
        println()
    }

    /***
     * 冒泡排序法
     * 思想：相邻两个元素比较大小，后一个元素比前一个元素大的话交换两个位置的值，然后接着进行比较
     * */
    fun test9() {
        val intArray = arrayOf(5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8)
        for (j in intArray.size - 1 downTo 0) {
            for (i in 0 until j) {
                if (intArray[i] > intArray[i + 1]) {
                    val temp = intArray[i + 1]
                    intArray[i + 1] = intArray[i]
                    intArray[i] = temp
                }
            }
        }
        for (value in intArray) {
            print("$value,")
        }
        println()
    }

    /**
     * 快速排序法
     * 思想：从数组的末尾开始寻找比key小的值，每次j--,直到找到，然后交换[j]和[i]位置的值
     * 交换完毕后，从开头开始寻找比key大的值，每次i++直到找到，然后交换[i]和[j]位置的值
     * 当i==j的时候结束本轮寻找，并确定了临界位置=i=j
     * 然后将数组从临界点分开，再用此方法排序左边和右边的数组
     * */
    fun test10() {
        val intArray = arrayOf(5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8)
        var i = 0
        var j = intArray.size - 1
        quickSort(i, j, intArray)
        for (value in intArray) {
            print("$value,")
        }
        println("结束")
    }

    private fun quickSort(startIndex: Int, endIndex: Int, intArray: Array<Int>) {
        if (endIndex <= 0 || startIndex >= endIndex)
            return
        var i = startIndex
        var j = endIndex
        val key = intArray[i]
        while (true) {//当i和j相等的时候结束循环
            while (intArray[j] >= key && j > 0) {//j>0防止索引越界
                j--
            }
            if (i >= j) break//当i==j的时候没有必要再交换值了，索引都一样；i>j时就无法找到分界点了
            var temp = intArray[j]
            intArray[j] = intArray[i]
            intArray[i] = temp
            while (intArray[i] < key && i < (intArray.size - 1)) {//防止索引越界
                i++
            }
            if (i >= j) break//当i==j的时候没有必要再交换值了，索引都一样；i>j时就无法找到分界点了
            temp = intArray[j]
            intArray[j] = intArray[i]
            intArray[i] = temp
        }
        quickSort(startIndex, i - 1, intArray)//i-1 是因为分界点的值不需要再进行比较，因为key肯定不小于左边，不大于右边
        quickSort(i + 1, endIndex, intArray)//i+1 是因为分界点的值不需要再进行比较，因为key肯定不小于左边，不大于右边
    }

    /**
     * 打印一个字符串中的所有回文字符串
     * 思想：遍历每一个字符，然后对比该字符跟后一个字符是否相同，相同的话再对比前一个跟后一个的后一个字符是否相等，
     * */
    fun test11() {
        val targetStr = "12233abccbahello"
        for (i in 0..targetStr.length - 2) {
            var precharIndex = i
            var nextcharIndex = i + 1
            var flag = false
            while (targetStr.get(precharIndex) == targetStr.get(nextcharIndex)) {
                precharIndex--
                nextcharIndex++
                if (precharIndex < 0 || nextcharIndex > (targetStr.length - 1)) {
                    break
                }
                flag = true
            }
            if (flag) {
                precharIndex++
                nextcharIndex--
            }
            if (flag) {
                print("${targetStr.substring(precharIndex, nextcharIndex + 1)},")
            }
        }
        println()
    }

    fun test12() {
        val tempString = "we are world"
        //方式一
        val resultString = tempString.replace(" ", "%20", true)
        println("origin:$tempString")
        println("result:$resultString")
        //方式二
        val sb = StringBuilder()
        for (char in tempString.toCharArray()) {
            if (char == ' ') {
                sb.append("%20")
            } else {
                sb.append(char)
            }
        }
        println("result2:${sb.toString()}")
    }


    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头
     */
    fun test13() {
        /*val numNote1 = Note(2, Note(3, Note(5, null)))
        val numNote2 = Note(7, Note(8, Note(9, null)))*/
        val numNote1 = Note(9, Note(9, Note(9, null)))
        val numNote2 = Note(9, Note(9, Note(9, Note(9, Note(9, Note(9, null))))))
        var tempNote1: Note? = numNote1
        var tempNote2: Note? = numNote2
        var resultNote = Note(0, null)
        val resultNote2 = resultNote
        //计算结果
        while (true) {
            var value1 = 0
            var value2 = 0
            if (tempNote1 == null && tempNote2 == null)//如果遍历结束的话
                break
            if (tempNote1 != null) {
                value1 = tempNote1.value
            }
            if (tempNote2 != null) {
                value2 = tempNote2.value
            }
            val result = value1 + value2 + resultNote.value
            if (result > 9) {
                resultNote.value = result % 10
                resultNote.next = Note(result / 10, null)
            } else {
                resultNote.value = result
                resultNote.next = Note(0, null)
            }
            resultNote = resultNote.next!!
            tempNote1 = tempNote1?.next
            tempNote2 = tempNote2?.next
        }
        //输出结果
        printNoteValue(numNote1)
        printNoteValue(numNote2)
        printNoteValue(resultNote2)
    }

    private fun printNoteValue(note: Note?) {
        var tempNote = note
        while (tempNote != null) {
            print(tempNote.value)
            tempNote = tempNote.next
        }
        println()
    }

    //定义链表的节点
    data class Note(var value: Int, var next: Note?)


    /**
     * 求最大容器(容器不能倾斜)
     * 找出柱状图间最大容器对应的值
     * */
    fun test14() {
        val intArray = arrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
        var maxSpace = 0
        var maxStartIndex = 0
        var maxEndIndex = 0
        for (i in intArray.indices) {
            for (j in i + 1 until intArray.size) {
                val height = Math.min(intArray[i], intArray[j])
                val with = j - i
                if (with * height > maxSpace) {
                    maxSpace = with * height
                    maxStartIndex = i
                    maxEndIndex = j
                }
            }
        }
        println("最大容器：$maxSpace，所在区间为：[$maxStartIndex,${intArray[maxStartIndex]}]-[$maxEndIndex,${intArray[maxEndIndex]}]")
    }

    /**
     * 找出数组中所有的不重复的任意三个元素的和为0的数据,ag(-1,0,1)、(-10,11,-1)
     * 下面这种方法属于暴力计算法，时间复杂度为O（n3），效率比较低，而且有重复数据
     * */
    fun test15() {
        val intArray = arrayOf(-1, 0, 1, 2, -1, -4, -10, 11, 13, -6, -7, 8, 12, -9, -1, 0, 1)
        for (i in intArray.indices) {
            for (j in i + 1 until intArray.size) {
                for (k in j + 1 until intArray.size) {
                    val result = intArray[i] + intArray[j] + intArray[k]
                    if (result == 0) {
                        print("[${intArray[i]},${intArray[j]},${intArray[k]}],")
                    }
                }
            }
        }
        println()
    }

    /**
     * 使用排序+双指针的方法找出数组中所有三个数相加等于0的组合(不重复)
     * 这个方法不考虑组合中元素的顺序
     * */
    fun test15_1() {
        val intArray = arrayOf(-1, 0, 1, 2, -1, -4, -10, 11, 13, -6, -7, 8, 12, -9, -1, 0, 1)
        val result: MutableList<Array<Int>> = MutableList(0) {
            arrayOf()
        }
        if (intArray.size < 4) {
            println(intArray)
        }
        intArray.sort()
        for (i in intArray.indices) {
            if (i > 0 && intArray[i - 1] == intArray[i])
                continue
            val sum = -intArray[i]
            var left = i + 1
            var right = intArray.size - 1
            while (left < right) {
                if (sum == (intArray[left] + intArray[right])) {
                    result.add(arrayOf(intArray[i], intArray[left], intArray[right]))
                    while (left < right && intArray[left] == intArray[left + 1]) left++
                    while (left < right && intArray[right] == intArray[right - 1]) right--
                    left++
                    right--
                } else if (sum < (intArray[left] + intArray[right])) {
                    right--
                } else {
                    left++
                }
            }
        }
        for (element in result) {
            print("[${element[0]}, ${element[1]}, ${element[2]}],")
        }
        println()
    }


    /**
     * 找出数组中所有的不重复的两个数的和为0的组合
     * 借鉴排序+双指针的方式
     * */
    fun test15_2() {
        val intArray = arrayOf(10, 8, -9, 1, -6, -10, 5, 6, -8, 11, 26, -13, 12, 10, 5, 6)
        if (intArray.size < 3) {
            println(intArray)
        }
        intArray.sort()
        var left = 0
        var right = intArray.size - 1
        while (left < right) {
            if (left > 0 && intArray[left] == intArray[left - 1]) {
                left++
                right--
                continue
            }
            if (-intArray[left] == intArray[right]) {
                print("[${intArray[left]},${intArray[right]}],")
                left++
                right--
            } else if (-intArray[left] > intArray[right]) {
                left++
            } else {
                right--
            }
        }
        println()
    }

//   {[()]}010101
//    ((())) 000111
//    (()())001011
//    )))(((111000
//    {[()}]}
//当前如果是左括号，下一个需要是左括号(子类)或同类右括号
//当前如果是右括号，前一个需要是右括号(子类)或逐次往后找直到找到匹配左括号且找到的位置索引>=0且找到的位置索引跟当前索引差为偶数

    fun test16() {
        test16_3(")")
        test16_3(")))))")
        test16_3("(")
        test16_3("((((((")
        test16_3("{[()}]}")
        test16_3("{[()]}]}")
        test16_3("{[{{[()]}}]}")
        test16_3("{{[()[()]]}}")
        test16_3("()[]{}{[(")
        test16_3("{[((((()))))][]()}{}()")
    }


    /**
     * 第三种方式，这种方式是官方推荐的，利用栈来更快实现，
     * 但是这种方法对于要求括号必须是正确包含关系时就不能正确判断了(正常的逻辑是大括号包含中括号包含小括号),ag{[{{[()]}}]}
     * */
    private fun test16_3(inputString: String?) {
        if (inputString == null || inputString.isEmpty() || inputString.length % 2 == 1) {
            println("$inputString 不是合法的字符串")
            return
        }
        val map = HashMap<String, Char>()
        val stack = Stack<Char>()
        map.put(")", '(')
        map.put("]", '[')
        map.put("}", '{')
        for (char in inputString.toCharArray()) {
            if (map.containsValue(char)) {//如果是左操作符的话，将符号放到栈中
                stack.push(char)
            } else {//如果是右操作符的话，从栈顶取数据，然后跟char比较
                if (stack.isEmpty()) {//当字符串第一个字符都不满足条件时，直接pop栈是会抛出异常的，这样的字符串也是不合法的
                    stack.push(' ')//向栈中加入一个字符，循环结束的时候帮助判断字符串是不合法的
                    break
                }
                val topChar = stack.pop()
                if (topChar != null && map.get(char.toString()) == topChar) {
                } else {
                    break
                }
            }
        }
        if (stack.empty()) {
            println("$inputString 是合法的字符串")
        } else {
            println("$inputString 不是合法的字符串")
        }
    }

    /**方式二（网友提出的），这种方式跟栈的原理差不多，只是使用了String中提供的方法，相对栈性能要差一点
    但是这种方法对于要求括号必须是正确包含关系时就不能正确判断了(正常的逻辑是大括号包含中括号包含小括号),ag{[{{[()]}}]}*/
    private fun test16_2(inputString: String?) {
        if (inputString == null || inputString.isEmpty()) {
            println("$inputString 不是合法的字符串")
            return
        }
        val length = inputString.length / 2
        var s: String = inputString
        for (i in 0..length) {
            s = s.replace("()", "").replace("[]", "").replace("{}", "")
        }
        if (s.isEmpty()) {
            println("$inputString 是合法的字符串")
        } else {
            println("$inputString 不是合法的字符串")
        }
    }

    /**方式三，自己实现的，代码量较多,但是没有上述提到的问题*/
    private fun test16_1(inputString: String?) {
        if (inputString == null || inputString.isEmpty() || inputString.length % 2 == 1) {
            println("$inputString 不是合法的字符串")
            return
        }
        var legal = true
        val charArray = inputString.toCharArray()
        for (index in charArray.indices) {
            if (isLeft(charArray[index])) {
                if (index == charArray.size - 1) {
                    legal = false
                    break
                }
                val result1 = isChildNext(charArray[index], charArray[index + 1])
                if (result1) {//只要括号包含关系是对的就是合法。对于下一个括号如果是右括号，那么配对问题交给下个if判断
                } else {
                    legal = false
                    break
                }
//                val result2 = isSameTypeRight(charArray[index], charArray[index + 1])
//                if (!(result1 || result2)) {//只要有一个条件合法
//                    legal = false
//                    break
//                }
            } else if (isRight(charArray[index])) {
                if (index == 0) {
                    legal = false
                    break
                }
                val result1 = isChildPre(charArray[index], charArray[index - 1])
                val result2 = findMatchLeft(charArray, index)
                if (result1 && result2) {//只有两个条件都合法时才合法，因为右边的括号还要找到配对的括号，否则就是不合法
                } else {
                    legal = false
                    break
                }
            } else {
                legal = false
            }
        }
        if (legal) {
            println("$inputString 是合法的字符串")
        } else {
            println("$inputString 不是合法的字符串")
        }
    }

    private fun findMatchLeft(charArray: CharArray, currentIndex: Int): Boolean {
        val currentChar = charArray[currentIndex]
        var index = currentIndex
        var findFlag = false
        while (index > 0) {
            index--
            if (isSameTypeRight(charArray[index], currentChar) && (currentIndex - index) % 2 == 1) {
                findFlag = true
                break
            }
        }
        return findFlag
    }

    private fun isSameTypeRight(currentChar: Char, nextChar: Char): Boolean {
        if (currentChar == '(' && nextChar == ')') {
            return true
        }
        if (currentChar == '[' && nextChar == ']') {
            return true
        }
        if (currentChar == '{' && nextChar == '}') {
            return true
        }
        return false
    }

    private fun isChildNext(currentChar: Char, nextChar: Char): Boolean {
        if (isRight(nextChar)) {
            return true
        }
        if (currentChar == '(' && nextChar == '(') {
            return true
        }
        if (currentChar == '[' && (nextChar == '(' || nextChar == '[')) {
            return true
        }
        if (currentChar == '{' && (nextChar == '(' || nextChar == '[' || nextChar == '{')) {
            return true
        }
        return false
    }

    private fun isChildPre(currentChar: Char, nextChar: Char): Boolean {
        if (isLeft(nextChar)) {
            return true
        }
        if (currentChar == ')' && nextChar == ')') {
            return true
        }
        if (currentChar == ']' && (nextChar == ')' || nextChar == ']')) {
            return true
        }
        if (currentChar == '}' && (nextChar == ')' || nextChar == ']' || nextChar == '}')) {
            return true
        }
        return false
    }

    private fun isLeft(char: Char): Boolean {
        return when (char) {
            '(' -> true
            '[' -> true
            '{' -> true
            else
            -> false
        }
    }

    private fun isRight(char: Char): Boolean {
        return when (char) {
            ')' -> true
            ']' -> true
            '}' -> true
            else
            -> false
        }
    }
}

