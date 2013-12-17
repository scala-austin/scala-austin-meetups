package org.yourorg.extn.scalaz


import _root_.scalaz.{\/, Free, Kleisli, NonEmptyList, ReaderT, ValidationNel}


trait ReaderNel[E] {
  type DNel[b] = NonEmptyList[E] \/ b
  type VNel[b] = ValidationNel[E, b]
  type ForDisjunction[a, b] = ReaderT[DNel, a, b]
  type ForValidation[a, b] = ReaderT[VNel, a, b]
}


object ReaderNel {

  def apply[A, B, E](f: A => ValidationNel[E, B])
      : ReaderValidationNel[A, B, E] =
    Kleisli.kleisli[ReaderNel[E]#VNel, A, B](f)

  def disjunction[A, B, E]
      (reader: ReaderValidationNel[A, B, E])
      : ReaderDisjunctionNel[A, B, E] =
    reader.mapK[ReaderNel[E]#DNel, B] { _.disjunction }

  def validation[A, B, E]
      (reader: ReaderDisjunctionNel[A, B, E])
      : ReaderValidationNel[A, B, E] =
    reader.mapK[ReaderNel[E]#VNel, B] { _.validation }

}
