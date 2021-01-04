import scala.io.Source

object ReportRepair extends App {

  val fname = "src/reportrepair.txt"
  val fSource = Source.fromFile(fname)
  val strArr = fSource.getLines().toArray
  fSource.close()

  val arr = strArr.flatMap(_.toIntOption)

  def checkSums(x: Int = 0, y: Int = 1): Unit = {
    if (y < arr.length) {
      if (arr(x) + arr(y) == 2020) {
        println(s"${arr(x)} + ${arr(y)} = ${arr(x) + arr(y)}")
        println(s"${arr(x)} x ${arr(y)} = ${arr(x) * arr(y)}")

      } else {
        checkSums(x, y + 1)
      }
    } else if (x < arr.length) {
      checkSums(x+1, x+2)
    } else {
      println("done")
    }
  }

  checkSums()
}
