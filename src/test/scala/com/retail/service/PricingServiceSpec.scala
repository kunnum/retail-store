package com.retail.service

import com.retail.domain.{Employee, Order, User}
import org.scalatest.WordSpec

/**
  * Created by meeraj on 23/11/2017.
  */
class PricingServiceSpec extends WordSpec with org.scalatest.Matchers {

  "Pricing service" should {
    "price employee users for non-grocery" in {
      val user = User(Employee, 10)
      val order = Order(200, false)
      PricingService.applyDiscount(user, order) shouldEqual 133
    }
    "price employee users for grocery" in {
      val user = User(Employee, 10)
      val order = Order(200, true)
      PricingService.applyDiscount(user, order) shouldEqual 140
    }
  }

}
