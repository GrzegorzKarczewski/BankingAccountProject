package org.example

enum class AccountType {
    DEBIT,CREDIT,CHECKING;  // Define account types

    companion object {
        fun fromInt(value: Int) = when (value) {
            1 -> DEBIT
            2 -> CREDIT
            3 -> CHECKING
            else -> null
        }
    }
}
interface BankAccount {
    fun createAccount(accountType: AccountType, accountName: String, initialDeposit:Double)
    fun checkBalance():Double
    fun withdraw(amount:Double): Boolean
    fun deposit(amount:Double): Boolean
}

 class Account(val accountType: AccountType) : BankAccount{

     private var currentBalance = 0.0
    override fun createAccount(accountType: AccountType, accountName: String, initialDeposit: Double) {
        currentBalance = initialDeposit
    return
    }

    override fun checkBalance(): Double {

    return this.currentBalance
    }

    override fun withdraw(amount: Double): Boolean {

        currentBalance -= amount
    return true
    }

    override fun deposit(amount: Double): Boolean {
        currentBalance += amount
    return true
    }


}

fun printSelectedOption( optionNumber:Int) {
    println("The selected option is $optionNumber")
}
// global vars
var userOnline = true

fun main() {
    // Making UI into function, for reuse later
    printUI()
    val input = readlnOrNull()?.toIntOrNull()  // Read and convert input to Int? once
    when (input) {
        null -> println("Wrong input")
        in 1..3 -> printSelectedOption(input)
        else -> println("Account type doesn't exist")
    }

// Convert Int to AccountType
    val accountType = AccountType.fromInt(input ?: 0) ?: return  // Return or handle invalid input

// Create account and process operations
    val account = setupAccount(accountType)
   while (userOnline) {
       accountOperations(account)
    }
}

 fun setupAccount(accountType: AccountType) : Account {

     val account = Account(accountType)
     account.createAccount(accountType, "Mister G,Karczewski", 515.0)
     println("You have created a ${account.accountType} account")
     println("The ${account.accountType} balance is ${account.checkBalance()} dollars")
/*
    }
    if (account.accountType == AccountType.CREDIT) {
        println("You have created a ${AccountType.CREDIT} account.")
        println("The ${account.accountType} balance is ${account.checkBalance()} dollars")

    }
    if (account.accountType == AccountType. CHECKING) {
        println("You have created a ${AccountType.CHECKING} account.")
        println("The ${account.accountType} balance is ${account.checkBalance()} dollars")

    }*/
     return account
}

fun printUI() {
    println("Welcome to the banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")
    println("Choose an option (1, 2 or 3): ")
}

fun accountOperations(account: Account) {
    println("What would you like to do?")
    println("1. Deposit")
    println("2. Withdraw")
    println("3. Check balance")
    println("4. Log out")

    val input = readlnOrNull()?.toIntOrNull()  // Read and convert input to Int? once
    when (input) {
        null -> println("Wrong input")
        1 -> deposit(account)
        2 -> withdraw(account)
        3 -> println("Balance of your ${account.accountType} account is $${account.checkBalance()}")
        4 -> userOnline = false
        else -> println("Account type doesn't exist")
    }
}

fun deposit(account:Account) {

    println("How much would you like to deposit? :")
    val amountToDeposit = readlnOrNull()?.toDoubleOrNull() // Read and convert input to Int? once
    if (amountToDeposit != null) {
        if (amountToDeposit > 0.0) {
            account.deposit(amountToDeposit)
        }
    }
    println("Account balance after deposit: ${account.checkBalance()}")
}
fun withdraw (account: Account) {
    println("How much would you like to withdraw: ")
    val amountToWithdraw = readlnOrNull()?.toDoubleOrNull() // Read and convert input to Int? once
    if (amountToWithdraw!= null) {
        if (amountToWithdraw < account.checkBalance()) {
            account.withdraw(amountToWithdraw)
        } else {
            println("The current balance is lower than the amount you want to withdraw")
        }
    }
    println("Account balance after withdraw: ${account.checkBalance()}")
}