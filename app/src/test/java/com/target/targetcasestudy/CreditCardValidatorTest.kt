package com.target.targetcasestudy

import com.target.targetcasestudy.model.validateCreditCard
import org.junit.Assert
import org.junit.Test

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {

  @Test
  fun `is credit card number valid`() {
    for(ccNum in VALID_CC_NUMBERS) {
      Assert.assertTrue(
        "valid credit card number should yield true: $ccNum",
        validateCreditCard(ccNum)
      )
    }
  }

  @Test
  fun `is credit card number invalid`() {
    for(ccNum in INVALID_CC_NUMBERS) {
      Assert.assertFalse(
        "Invalid credit card number should yield false: $ccNum",
        validateCreditCard(ccNum)
      )
    }
  }

  companion object {
    private val VALID_CC_NUMBERS = listOf(
      "4716299837005151",
      "6011352730436424",
      "30390553247486",
      "4913384407149729",
      "5109126507957076",
      "3534728238366046",
      "36154926558407",
      "6377225749141300",
      "349271074984169",
      "5562893061503535",
      "6304503402974656")
    private val INVALID_CC_NUMBERS = listOf(
      "4716299837005153",
      "6011352730436422",
      "4913384407149735",
      "5109126507957072",
      "3534728238366041",
      "36154926558405",
      "6377225749141301",
      "349271074984160",
      "5562893061503532",
      "6304503402974658"
    )
  }
}
