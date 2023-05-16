package com.example.bitcoin_ticker.core.validation

import com.example.bitcoin_ticker.R
import com.example.bitcoin_ticker.core.Constant.PASSWORD_LENGTH

data class PasswordRule(
    var rule: Int,
    var status: Boolean
)

class PasswordValidation {
    var ruleOne = PasswordRule(rule = R.string.password_rules_one, status = false)
    var ruleTwo = PasswordRule(rule = R.string.password_rules_two, status = false)
    var ruleThree = PasswordRule(rule = R.string.password_rules_three, status = false)
    var ruleFour = PasswordRule(rule = R.string.password_rules_four, status = false)
    var rules = mutableListOf<PasswordRule>()
    init {
        rules.add(ruleOne)
        rules.add(ruleTwo)
        rules.add(ruleThree)
        rules.add(ruleFour)
    }
    fun getRules(password: String): Boolean {
        rules[0].status = Regex(".*[A-Z].*").matches(password)
        rules[1].status = Regex(".*[a-z].*").matches(password)
        rules[2].status = Regex(".*\\d.*").matches(password)
        rules[3].status = password.length >= PASSWORD_LENGTH
        return rules[0].status && rules[1].status && rules[2].status && rules[3].status
    }
}
