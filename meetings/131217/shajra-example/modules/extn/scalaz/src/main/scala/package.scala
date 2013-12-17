package org.yourorg.extn


/** Functionality to assist working with the ScalaZ library. */
package object scalaz {

  type ReaderValidationNel[A, B, E] = ReaderNel[E]#ForValidation[A, B]

  type ReaderDisjunctionNel[A, B, E] = ReaderNel[E]#ForDisjunction[A, B]

  implicit class RichReaderValidationNel[A, B, E]
      (reader: ReaderValidationNel[A, B, E]) {
    def disjunction = ReaderNel disjunction reader
  }

  implicit class RichReaderDisjunctionNel[A, B, E]
      (reader: ReaderDisjunctionNel[A, B, E]) {
    def validation = ReaderNel validation reader
  }

}
