package com.retail.domain

/**
  * Represents a user of the system.
  *
  * @param userType Defines the type of the user.
  * @param noOfYears No of years the user has been a customer.
  */
case class User(userType: UserType, noOfYears: Int)

/**
  * Represents a user type.
  * @param label Label for the user.
  */
case class UserType(label: String)

/**
  * An employee user.
  */
object Employee extends UserType("EMPLOYEE")

/**
  * An affiliate user.
  */
object Affiliate extends UserType("AFFILIATE")

/**
  * A normal user.
  */
object Normal extends UserType("NORMAL")
