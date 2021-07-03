import org.sradyushkin.generate.DataGenerator
import org.sradyushkin.receive.DataReceiver

fun main() {
    val dataGenerator = DataGenerator()
    val dataReceiver = DataReceiver()
    dataGenerator.perform()
    dataReceiver.perform()
}