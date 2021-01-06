import java.io.{BufferedReader, FileReader}

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Trajectory2 extends App {

  val slopes = List(
    (1, 1),
    (3, 1),
    (5, 1),
    (7, 1),
    (1, 2)
  )

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

  def pathGen(rise: Int, run: Int, y: Int = 0, x: Int = 0): Int = {
    val pathBuf = new ArrayBuffer[Char]()

    @tailrec
    def getStep(y: Int, x: Int): Unit = {
      if (y < height) {
        pathBuf += arr(y)(x)
        getStep(y + rise, (x + run) % width)
      } else {
        pathBuf.toArray
      }
    }

    getStep(y + rise, x + run)
    val pathArr = pathBuf.toArray

    val trees = pathArr.filter(_ == '#')
    trees.length
  }

  val treeCounts = new ArrayBuffer[Int]()

  for (slope <- slopes) {
    val rise = slope._2
    val run = slope._1
    val treeCount = pathGen(rise, run)
    treeCounts += treeCount
  }

  println(treeCounts)
  var product: Long = 1
  treeCounts.foreach(count => product *= count)
  println(product)

//  println(s"Number of Trees: ${treeCount}")
}
