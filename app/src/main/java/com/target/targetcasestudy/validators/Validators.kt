package com.target.targetcasestudy.model

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
  if(creditCardNumber.isEmpty() || creditCardNumber.length < 13 || creditCardNumber.length > 19) {
    return false
  }

  // Step 1 - Drop last digit (save it)
  val lastDigit = creditCardNumber.last().toString().toInt()
  var modifiedNumber = creditCardNumber.substring(0, creditCardNumber.lastIndex)

  // Step 2 - Reverse the numbers
  modifiedNumber = modifiedNumber.reversed()

  // Step 3 - Multiply digits in odd positions by 2, subtract 9 from any result > 9
  val digits: IntArray = modifiedNumber.map { it.toString().toInt() }.toIntArray()
  var result = 0
  for((i, _) in digits.withIndex()) {
    if((i+1) % 2 != 0) digits[i] = digits[i] * 2 // i+1 because the CC formula is not 0 indexed
    if(digits[i] > 9) digits[i] = digits[i] - 9 // immediately subtract 9 if higher than 9
    result += digits[i] // Step 4 - sum digits
  }

  // Step 5 - Total + last digit mod 10 == 0
  return (result + lastDigit) % 10 == 0
}
