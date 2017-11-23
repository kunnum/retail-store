package com.retail.domain

case class User(userType: UserType, noOfYears: Int)

case class UserType(label: String)
object Employee extends UserType("EMPLOYEE")
object Affiliate extends UserType("AFFILIATE")
object Normal extends UserType("NORMAL")
