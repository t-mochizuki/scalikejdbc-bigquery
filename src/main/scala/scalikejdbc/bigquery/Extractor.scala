package scalikejdbc.bigquery

import scalikejdbc._

class Extractor[A](statement: SQLSyntax, f: WrappedResultSet => A) {

  def list: Runner[Seq[A]] = Runner(statement)(_.map(f)(collection.breakOut))

  def single: Runner[Option[A]] = Runner(statement)(_.map(f).headOption)
}

trait ExtractorBuilder {

  def statement: SQLSyntax

  def map[A](f: WrappedResultSet => A): Extractor[A] = new Extractor[A](statement, f)
}
