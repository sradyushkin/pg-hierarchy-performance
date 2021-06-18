import generate.DataGenerator
import receive.DataReceiver

fun main() {
    val dataGenerator = DataGenerator()
    val dataReceiver = DataReceiver()
    dataGenerator.perform()
    dataReceiver.perform()
}