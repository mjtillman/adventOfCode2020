import java.io.{BufferedReader, FileReader}

import scala.collection.mutable.ArrayBuffer

object Trajectory1 extends App {

//  class trajMap {
//    val trajMap: Array.ofDim[Char]
//  }

  val run = 3
  val rise = 1

  val fName = "src/trajectoryMap.txt"
  val fReader = new BufferedReader(new FileReader(fName))
  val arrBuf = new ArrayBuffer[Array[Char]]()

  def fHandler(newLine: String): Array[Array[Char]] = {
    if (newLine != null) {
      arrBuf += newLine.toCharArray
      fHandler(fReader.readLine())
    }
    arrBuf.toArray
  }

  val arr = fHandler(fReader.readLine())
  val width = arr(0).length
  val height = arr.length

  fReader.close()

//  println(arr.map(_.mkString(" ")).mkString("\n"))

  def pathGen(y: Int = 0, x: Int = 0): Array[Char] = {
    val pathBuf = new ArrayBuffer[Char]()

    def getStep(y: Int, x: Int): Unit = {
      if (y < height) {
//        println(s"($x, $y): ${arr(y)(x)}")
        pathBuf += arr(y)(x)

        getStep(y + rise, (x + run) % width)
      }
    }
    getStep(y + rise, x + run)

    pathBuf.toArray
  }

  val path = pathGen()
//  println(s"\nPath: ${path.mkString(" ")}")
  val treeCount = path.count(_ == '#')
  println(s"Number of Trees: ${treeCount}")
}
