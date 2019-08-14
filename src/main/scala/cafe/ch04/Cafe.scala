package cafe.ch04

import scala.util.{Failure, Success, Try}

// 협력을 개선해보자. 캐시어한테 주문할 때 커피를 못 받을 수도 있지않나?

case class Customer(cashier: Cashier, coffee: Option[Coffee] = None) {
  def order(order: Order): Customer = {
    val coffee = cashier.order(order)
    this.copy(coffee = coffee)
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

case class Order(coffeeName: String, quantity: Int)

sealed trait MenuBoard {
  def validate(order: Order): Boolean
}

case class Cashier(barista: Barista, menuBoard: MenuBoard) {
  def order(order: Order): Option[Coffee] =
    if (menuBoard.validate(order))
      Some(barista.makeCoffee(order))
    else
      None
}

case class Barista() {
  def makeCoffee(order: Order): Coffee = Coffee(order.coffeeName, order.quantity)
}

case class Coffee(name: String, quantity: Int) {
  def drunken(quantity: Int): Try[Coffee] =
    if (this.quantity < quantity)
      Failure(new IllegalStateException("Not enough coffee."))
    else
      Success(this.copy(quantity = this.quantity - quantity))
}