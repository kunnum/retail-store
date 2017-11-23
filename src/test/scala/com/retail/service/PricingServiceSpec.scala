package com.retail.service

import com.retail.domain._
import org.scalatest.WordSpec

/**
  * Test case for the pricing service.
  */
class PricingServiceSpec extends WordSpec with org.scalatest.Matchers {

  "Pricing service" should {

    "price employee users for non-grocery" in {
      implicit val user = User(Employee, 10)
      val order = Order(200, false)
      PricingService.applyDiscount(order) shouldEqual 133
    }

    "price employee users for grocery" in {
      implicit val user = User(Employee, 10)
      val order = Order(200, true)
      PricingService.applyDiscount(order) shouldEqual 140
    }

    "price affiliate users for non-grocery" in {
      implicit val user = User(Affiliate, 10)
      val order = Order(200, false)
      PricingService.applyDiscount(order) shouldEqual 171
    }

    "price affiliate users for grocery" in {
      implicit val user = User(Affiliate, 10)
      val order = Order(200, true)
      PricingService.applyDiscount(order) shouldEqual 180
    }

    "price loyal users for non-grocery" in {
      implicit val user = User(Normal, 10)
      val order = Order(200, false)
      PricingService.applyDiscount(order) shouldEqual 180.5
    }

    "price loyal users for grocery" in {
      implicit val user = User(Normal, 10)
      val order = Order(200, true)
      PricingService.applyDiscount(order) shouldEqual 190
    }

    "price normal users for non-grocery" in {
      implicit val user = User(Normal, 1)
      val order = Order(200, false)
      PricingService.applyDiscount(order) shouldEqual 190
    }

    "not price normal users for grocery" in {
      implicit val user = User(Normal, 1)
      val order = Order(200, true)
      PricingService.applyDiscount(order) shouldEqual 200
    }

  }

}
