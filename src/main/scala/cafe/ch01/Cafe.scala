package cafe.ch01


case class Todd(eileen: Eileen) {
  def order(): Coffee = eileen.order("americano")
}

case class Eileen(shell: Shell) {
  def order(coffeeName: String): Coffee = shell.makeCoffee(coffeeName)
}

case class Shell() {
  def makeCoffee(coffeeName: String): Coffee = Coffee(coffeeName)
}

case class Coffee(name: String)

