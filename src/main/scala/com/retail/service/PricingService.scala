package com.retail.service

import com.retail.domain.{Affiliate, Employee, Order, User}

/**
  * Entry point to the pricing service.
  *
  * TODO This should really be implemented as a chain of monads, maybe for another day.
  */
object PricingService {

  /**
    * Prices an order based on the user type and characteristics of the order.
    *
    * @param user User who is placing the order.
    * @param order Order that is being placed.
    * @return The discounted price of the order.
    */
  def applyDiscount(order: Order)(implicit user: User): BigDecimal = {
    val pricer = user match {
      case User(Employee, _) => EmployeePricer
      case User(Affiliate, _) => AffiliatePricer
      case _ => LoyaltyPricer
    }
    BulkPricer.price(pricer.price(order)).value
  }

  /*
   * Apply a bulk price on all orders.
   */
  private def bulkPrice(value: BigDecimal) = value * 0.95
}

/**
  * An abstract pricer.
  */
trait Pricer {
  /**
    * Prices the order.
    * @param user User for whom order is being discounted.
    * @param order Order to be priced.
    * @return Discounted price.
    */
  def price(order: Order)(implicit user: User): Order
}

/**
  * Pricing object for employees.
  */
object EmployeePricer extends Pricer {
  /**
    * Offers a 30% discount.
    * @param user User for whom order is being discounted.
    * @param order Order to be priced.
    * @return Discounted price.
    */
  def price(order: Order)(implicit user: User) = order.copy(value = order.value * 0.7)
}

/**
  * Pricing object for affiliates.
  */
object AffiliatePricer extends Pricer {
  /**
    * Offers 10% discount.
    * @param user User for whom order is being discounted.
    * @param order Order to be priced.
    * @return Discounted price.
    */
  def price(order: Order)(implicit user: User) = order.copy(value = order.value * 0.9)
}

/**
  * Loyalty based pricer.
  */
object LoyaltyPricer extends Pricer {
  /**
    * Offers 5% discount.
    * @param user User for whom order is being discounted.
    * @param order Order to be priced.
    * @return Discounted price.
    */
  def price(order: Order)(implicit user: User) = if (user.noOfYears > 2) order.copy(value = order.value * 0.95) else order
}

/**
  * Bulk pricing for non-grocery items.
  */
object BulkPricer extends Pricer {
  /**
    * Offers 5% discount for non grocery items.
    * @param user User for whom order is being discounted.
    * @param order Order to be priced.
    * @return Discounted price.
    */
  def price(order: Order)(implicit user: User) = if (!order.isGrocery) order.copy(value = order.value * 0.95) else order
}
