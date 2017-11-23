package com.retail.service

import com.retail.domain.{Affiliate, Employee, Order, User}

object PricingService {
  def applyDiscount(user: User, order: Order): BigDecimal = {
    val pricer = user match {
      case User(Employee, _) => EmployeePricer
      case User( Affiliate, _) => AffiliatePricer
      case u => if (u.noOfYears > 2) LoyaltyPricer else NilPricer
    }
    val price = pricer.price(order)
    if (!order.isGrocery) bulkPrice(price) else price
  }

  private def bulkPrice(value: BigDecimal) = value * 0.95
}

trait Pricer {
  def price(order: Order): BigDecimal
}

object EmployeePricer extends Pricer {
  def price(order: Order) = order.value * 0.7
}

object AffiliatePricer extends Pricer {
  def price(order: Order) = order.value * 0.9
}

object LoyaltyPricer extends Pricer {
  def price(order: Order) = order.value * 0.95
}

object NilPricer extends Pricer {
  def price(order: Order) = order.value
}
