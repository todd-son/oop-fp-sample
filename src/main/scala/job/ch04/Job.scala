package job.ch04

import job.ch04.Status._

// 작업은 사실 step이 실패하면 종료되어야 함

// harmful step의 상태에 직접 접근했다. val results = steps.takeWhile(step => step.execute().status == Completed)
case class Job(steps: List[Step]) {
  def execute(): Job = {
    val results = steps.takeWhile(step => step.execute().status == Completed)

    this.copy(steps = results ++ steps.drop(results.size))
  }

  def status: Status =
    if (steps.forall(step => step.status == Completed))
      Completed
    else if (steps.forall(step => step.status == Initialized))
      Initialized
    else if (steps.exists(step => step.status == Failed))
      Failed
    else
      Started

}

sealed trait Step {
  def execute(): Step

  val status: Status
}

case class LoadStep(status: Status) extends Step {
  override def execute(): Step = ???
}

case class MappingStep(status: Status) extends Step {
  override def execute(): Step = ???
}

sealed trait Status

object Status {
  case object Initialized extends Status

  case object Started extends Status

  case object Completed extends Status

  case object Failed extends Status
}


