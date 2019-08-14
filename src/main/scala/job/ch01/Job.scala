package job.ch01

case class Job(steps: List[Step]) {
  def execute(): Boolean = steps.forall(step => step.execute())
}

case class Step() {
  def execute(): Boolean = true
}
