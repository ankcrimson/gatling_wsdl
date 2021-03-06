package wsdltest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicWSDLCurrencyConv extends Simulation {

  val httpConf = http
    .baseURL("http://www.webservicex.com") // Here is the root for all relative URLs
  
  val scn = scenario("Test WSDL") // A scenario is a chain of requests and pauses
    .exec(http("post_wsdl") // Here's an example of a POST request
      .post("/CurrencyConvertor.asmx") //wsdl path
      .header("Content-Type", "text/xml;charset=UTF-8") //content type
      .header("SOAPAction", "http://www.webserviceX.NET/ConversionRate") //soap action
      .body(RawFileBody("data/body.xml")) //data
      )

  setUp(scn.inject(rampUsers(10) over (10 seconds)).protocols(httpConf))
}
