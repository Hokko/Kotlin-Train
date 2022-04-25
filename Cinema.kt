package cinema
const val STD = 60

fun main() {
    println("Enter the number of rows: ")
    val inputRows = readln().toInt()
    println("Enter the number of seats in each row: ")
    val inputSeats = readln().toInt()
    val seatsAll = inputRows * inputSeats
    val space = mutableListOf(mutableListOf<String>())
    val seatList = mutableListOf(1)
    for (i in 0 until inputSeats) {
        space[0].add(i, "S")
    }
    for (num in 1 until inputSeats) {
        seatList.add(num + 1)
        space.add(mutableListOf())
        for (i in 0 until inputSeats) {
            space[num].add(i, "S")
        }
    }
    var totalIncome = 0
    totalIncome = if (seatsAll <= STD) seatsAll * 10 else {
        if (inputRows % 2 != 0) {
            inputRows / 2 * inputSeats * 10 + (inputRows / 2 + 1) * inputSeats * 8
        } else inputRows / 2 * inputSeats * 10 + inputRows / 2 * inputSeats * 8
    }

    var income = 0
    var ticketStat = 0
    do {
        println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit")
        val input = readln().toInt()

        when (input) {
            2 -> {
                do {
                    var checkTick = 0
                    try {
                        println("Enter a row number: ")
                        val rowNum = readln().toInt()
                        println("Enter a seat number in that row: ")
                        val seatNum = readln().toInt()

                        if (space[rowNum - 1][seatNum - 1] == "B") {
                            println("That ticket has already been purchased!")
                        } else {
                            income += if (seatsAll <= STD) {
                                println("Ticket price: $10")
                                10
                            } else {
                                if (inputRows / 2 >= rowNum) {
                                    println("Ticket price: $10")
                                    10
                                } else {
                                    println("Ticket price: $8")
                                    8
                                }
                            }
                            space[rowNum - 1][seatNum - 1] = "B"
                            checkTick++
                            ticketStat++
                        }
                    } catch (e: Exception) {
                        println("Wrong input!")
                    }
                } while (checkTick == 0)
            }
            1 -> {
                println("Cinema:")
                println("  ${seatList.joinToString(" ")}")
                for (i in 1..inputRows) {
                    println("$i ${space[i - 1].joinToString(" ")}")
                }
            }
            3 -> {
                val percentage:Double = ticketStat.toDouble() / seatsAll.toDouble() * 100
                println("Number of purchased tickets: $ticketStat")
                println("Percentage: ${"%.2f".format(percentage)}%")
                println("Current income: $$income")
                println("Total income: $$totalIncome")
            }
        }
    } while (input != 0)
}