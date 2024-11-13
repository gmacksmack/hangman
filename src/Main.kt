import kotlin.random.Random

val possibleWords = listOf("KOTLIN", "ANDROID", "DEVELOPER", "FUNCTION", "VARIABLE", "INHERITANCE", "POLYMORPHISM", "LAMBDA", "ABSTRACTION", "ENCAPSULATION",
    "COMPILE", "DEBUGGING", "RECURSION", "ASYNC", "COROUTINE",
    "DATABASE", "INTERFACE", "OVERRIDE", "CONSTRUCTOR", "EXTENSION")
var keyWord = ""
val guesses = arrayListOf<Char>()
var remainingGuesses = 7
var mistakes = 0


fun main() {
    setUpGame()
}

fun setUpGame(){
    val wordIndex = Random.nextInt(possibleWords.size)
    keyWord = possibleWords[wordIndex]
    println(keyWord)

    for (i in keyWord.indices)
        guesses.add('_')

        var gameOver = false
    do {
        printGameStatus()
        println("Please enter a letter:")
        val input = readLine()?: ""

        if (input.isEmpty() || input.length > 1 || !input[0].isLetter()) {
            println("'$input' is not valid. Please enter a letter A - Z\n")
        } else {
            val letter = input[0].uppercaseChar()
            if(keyWord.contains(letter)) {
                for (i in keyWord.indices) {
                    if(keyWord[i] == letter)
                        guesses[i] = letter
                }
                if(!guesses.contains('_'))
                    gameOver = true
            } else {
                println("Sorry, that letter is not in the word")
                remainingGuesses--
                mistakes++

                if(mistakes == 7)
                    gameOver = true
            }
        }
    } while (!gameOver)

    if(mistakes == 7) {
        printGameStatus()
        println("Sorry, you lost! The word was \n$keyWord")
    } else {
        println("WINNER! You correctly guessed  \n$keyWord!")
    }
}

fun printGameStatus() {
    when (mistakes) {
        0 -> print0Mistakes()
        1 -> print1Mistake()
        2 -> print2Mistakes()
        3 -> print3Mistakes()
        4 -> print4Mistakes()
        5 -> print5Mistakes()
        6 -> print6Mistakes()
        7 -> print7Mistakes()
    }

    print("Your word is: \n")
    for (element in guesses)
        print("$element ")
    println("\nYou have $remainingGuesses guess(es) to solve the word!")
}