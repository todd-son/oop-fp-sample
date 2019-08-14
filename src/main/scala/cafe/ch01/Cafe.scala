package cafe.ch01


case class Todd(eileen: Eileen) {
  def order(): Americano = eileen.order("americano")
}

case class Eileen(shell: Shell) {
  def order(coffeeName: String): Americano = shell.makeCoffee(coffeeName)
}

case class Shell() {
  def makeCoffee(coffeeName: String): Americano = Americano()
}

case class Americano()

