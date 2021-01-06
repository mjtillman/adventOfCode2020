import java.io.{BufferedReader, FileReader}
import scala.annotation.tailrec

object Toboggan2 extends App {

  val fName = "src/toboggan.txt"
  val fReader = new BufferedReader(new FileReader(fName))

  var validCount = 0

  @tailrec
  def fHandler(newLine: String): Unit = {
    if (newLine != null) {
      validatePass(newLine)
      fHandler(fReader.readLine())

    } else {
      println(s"Validation completed: $validCount valid passwords found.")
    }
  }

  fHandler(fReader.readLine())

  fReader.close()

  def validatePass(newLine: String): Unit = {

    val pass = newLine.substring(newLine.indexOf(":") + 2)
    val place1 = newLine.substring(0, newLine.indexOf("-")).toInt - 1
    val place2 = newLine.substring(newLine.indexOf("-") + 1, newLine.indexOf(" ")).toInt - 1
    val letter = newLine.charAt(newLine.indexOf(" ") + 1)

    val char1 = pass.charAt(place1)
    val char2 = pass.charAt(place2)

    if (char1 == letter ^ char2 == letter) validCount += 1
  }
}
