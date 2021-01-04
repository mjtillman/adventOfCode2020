
  import scala.annotation.tailrec
  import scala.io.Source

  object ReportRepair2 extends App {

    val fname = "src/reportrepair.txt"
    val fSource = Source.fromFile(fname)
    val strArr = fSource.getLines().toArray
    fSource.close()

    val arr = strArr.flatMap(_.toIntOption)

    @tailrec
    def checkSums(x: Int = 0, y: Int = 1, z: Int = 2): Unit = {
      if (z < arr.length) {

        val sum = arr(x) + arr(y) + arr(z)

        if (sum == 2020) {
          println(s"${arr(x)} + ${arr(y)} + ${arr(z)} = $sum")
          println(s"${arr(x)} x ${arr(y)} x ${arr(z)} = ${arr(x) * arr(y) * arr(z)}")
        } else {
          checkSums(x, y, z + 1)
        }

      } else if (y < arr.length - 1) {
        checkSums(x, y+1, y+2)
      } else if (x < arr.length - 2) {
        checkSums(x+1, x+2, x+3)
      }
    }

    checkSums()

}
