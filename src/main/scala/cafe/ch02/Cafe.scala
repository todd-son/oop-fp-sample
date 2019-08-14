package cafe.ch02

import scala.util.{Failure, Success, Try}

case class Todd(eileen: Eileen, coffee: Option[Americano] = None) {
  def order(coffeeName: String, quantity: Int): Todd = {
    val coffee = eileen.order(coffeeName, quantity)
    this.copy(coffee = Some(coffee))
  }

  def drinkCoffee(quantity: Int): Try[Todd] = {
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

case class Eileen(shell: Shell) {
  def order(coffeeName: String, quantity: Int): Americano = shell.makeCoffee(coffeeName, quantity)
}

case class Shell() {
  def makeCoffee(coffeeName: String, quantity: Int): Americano = Americano(quantity)
}

case class Americano(quantity: Int) {
  def drunken(quantity: Int): Try[Americano] =
    if (this.quantity < quantity)
      Failure(new IllegalStateException("Not enough coffee."))
    else
      Success(this.copy(quantity = this.quantity - quantity))
}