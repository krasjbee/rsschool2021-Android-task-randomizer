package com.rsschool.android2021

interface Router {
    fun toSecondFragment(min:Int,max:Int)

    fun toFirstFragment(previousNumber:Int)
}