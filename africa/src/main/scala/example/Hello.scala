package example

import scala.io.Source
import java.io.FileOutputStream
import java.io.PrintStream
import io.circe.parser._, io.circe.generic.auto._ //, io.circe._, io.circe.Decoder, cats.syntax.either._
import io.circe.{Encoder, Json}



object Hello extends Greeting with App {
  print( greeting )

  def source = Source.fromURL(
    "https://raw.githubusercontent.com/mledoze/countries/master/countries.json"
  )

  case class CountryName( official: String)
  case class Country( name: CountryName, capital: List[String], region: String, area: Float)
  case class CountryOut( name: String, capital: String, area: Float)

  val str = source.mkString
  println( s" Data size: ${str.size}" )

  var dataIn : List[Country] = Nil

  val result = decode[ List[Country]]( str ) match {
       case Left( failure )    => println( "Failed: "+ failure )
       case Right( countries ) => dataIn = countries.filter( _.region == "Africa").sortBy(- _.area).take( 10 )
     }

  if (dataIn.nonEmpty) {
    val dataOut: List[CountryOut] = for( cou <- dataIn ) yield CountryOut( cou.name.official, cou.capital.head, cou.area)

    val out = io.circe.Encoder[ List[CountryOut] ].apply( dataOut ).toString()
    println("10 largest african countries:")
    println( out )

    val outputFile = args( 0 )
    val fos = new FileOutputStream( outputFile )
    val printer = new PrintStream( fos )
    printer.println( out )
    source.close()
    printer.close()

    println("")
    println( s"Writen to file: $outputFile")
  }


}

trait Greeting {
  lazy val greeting: String = "Hello!"
}
