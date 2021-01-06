import scala.io.Source

object Toboggan extends App {

  val fname = "src/toboggan.txt"
  val fSource = Source.fromFile(fname)
  val arr = fSource.getLines().toArray
  fSource.close()

  var validCount = 0

  def validatePass(min: Int, max: Int, foo: Char, pass: String): Unit = {
    val charCount = pass.count(_ == foo)
    if (charCount >= min && charCount <= max) validCount += 1
  }

  arr.map(arr => validatePass(
    arr.substring(0, arr.indexOf("-")).toInt,
    arr.substring(arr.indexOf("-") + 1, arr.indexOf(" ")).toInt,
    arr.charAt(arr.indexOf(" ") + 1),
    arr.substring(arr.indexOf(":") + 2)
  ))

  println(s"$validCount valid passwords")
}
