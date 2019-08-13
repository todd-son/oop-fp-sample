package cafe

case class Customer(cashier: Cashier) {
  def order(coffeeName: String): Coffee = cashier.order(coffeeName)
}

case class Cashier(barista: Barista) {
  def order(coffeeName: String): Coffee = barista.makeCoffee(coffeeName)
}

case class Barista() {
  def makeCoffee(coffeeName: String): Coffee = Coffee(coffeeName)
}

case class Coffee(name: String) {

}