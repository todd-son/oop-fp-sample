package cafe.ch02

import scala.util.{Failure, Success, Try}

case class Customer(cashier: Cashier, coffee: Option[Coffee] = None) {
  def order(coffeeName: String, quantity: Int): Customer = {
    val coffee = cashier.order(coffeeName, quantity)
    this.copy(coffee = Some(coffee))
  }

  def drinkCoffee(quantity: Int): Try[Customer] = {
    coffee match {
      case None => Failure(new IllegalStateException("Don't have a cup of coffee."))
      case Some(c) =>
        c.drunken(quantity) match {
          case Success(c) => Success(this.copy(coffee = Some(c)))
          case Failure(e) => Failure(e)
        }
    }
  }
}

case class Cashier(barista: Barista) {
  def order(coffeeName: String, quantity: Int): Coffee = barista.makeCoffee(coffeeName, quantity)
}

case class Barista() {
  def makeCoffee(coffeeName: String, quantity: Int): Coffee = Coffee(coffeeName, quantity)
}

case class Coffee(name: String, quantity: Int) {
  def drunken(quantity: Int): Try[Coffee] =
    if (this.quantity < quantity)
      Failure(new IllegalStateException("Not enough coffee."))
    else
      Success(this.copy(quantity = this.quantity - quantity))
}