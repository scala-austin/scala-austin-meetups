package org.yourorg.extn.scalaz


import scalaz.{\/, NonEmptyList, Validation, ValidationNel}


trait ToValidationNel[F[_, _]] {
  def apply[E, A](from: F[E, A]): ValidationNel[E, A]
}


object ToValidationNel {

  type DisjunctionNel[E, A] = NonEmptyList[E] \/ A

  implicit val fromValidationNel: ToValidationNel[ValidationNel] =
    new ToValidationNel[ValidationNel] {
      def apply[E, A](v: ValidationNel[E, A]) = v
    }

  implicit val fromValidation: ToValidationNel[Validation] =
    new ToValidationNel[Validation] {
      def apply[E, A](v: Validation[E, A]) = v.toValidationNel
    }

  implicit val fromDisjunctionNel: ToValidationNel[DisjunctionNel] =
    new ToValidationNel[DisjunctionNel] {
      def apply[E, A](v: DisjunctionNel[E, A]) = v.validation
    }

  implicit val fromDisjunction: ToValidationNel[\/] =
    new ToValidationNel[\/] {
      def apply[E, A](v: E \/ A) = v.validation.toValidationNel
    }

}
