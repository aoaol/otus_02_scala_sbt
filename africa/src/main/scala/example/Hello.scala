package example

import scala.io.Source
import java.io.FileOutputStream
import java.io.PrintStream
import io.circe.parser._
//import cats.syntax.either._



object Hello extends Greeting with App {
  println( greeting )

  def source = Source.fromURL(
    "https://raw.githubusercontent.com/mledoze/countries/master/countries.json"
  )

  case class CountryName( official: String)
  case class Country( name: CountryName, region: String, area:Int)

  val str = source.mkString

  val data = decode[ List[Country]]( str ) match {
       case Left(failure) => println(failure)
       case Right(countries) => countries.filter( _.region == "Africa").sortBy(- _.area).take( 10 )
     }

}

trait Greeting {
  lazy val greeting: String = "hello"
}
