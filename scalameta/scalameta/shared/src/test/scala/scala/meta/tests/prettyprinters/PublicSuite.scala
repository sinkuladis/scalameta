package scala.meta.tests
package prettyprinters

import org.scalatest._
import scala.meta._

class PublicSuite extends FunSuite {
  test("scala.meta.Dialect.toString") {
    // covered below
  }

  test("scala.meta.Tree.toString (manual)") {
    val tree = Term.ApplyInfix(Term.Name("foo"), Term.Name("+"), Nil, List(Term.Name("bar")))
    assert(tree.toString === "foo + bar")
  }

  test("scala.meta.Tree.show[Structure] (manual)") {
    val tree = Term.ApplyInfix(Term.Name("foo"), Term.Name("+"), Nil, List(Term.Name("bar")))
    assert(tree.show[Structure] === """Term.ApplyInfix(Term.Name("foo"), Term.Name("+"), Nil, Seq(Term.Name("bar")))""")
  }

  test("scala.meta.Tree.show[Syntax]") {
    val tree = Term.ApplyInfix(Term.Name("foo"), Term.Name("+"), Nil, List(Term.Name("bar")))
    assert(tree.show[Syntax] === "foo + bar")
  }

  test("scala.meta.Tree.toString (parsed)") {
    val tree = "foo + bar // baz".parse[Term].get
    assert(tree.toString === "foo + bar // baz")
  }

  test("scala.meta.Tree.show[Structure] (parsed)") {
    val tree = "foo + bar // baz".parse[Term].get
    assert(tree.show[Structure] === """Term.ApplyInfix(Term.Name("foo"), Term.Name("+"), Nil, Seq(Term.Name("bar")))""")
  }

  test("scala.meta.Tree.show[Syntax] (parsed)") {
    val tree = "foo + bar // baz".parse[Term].get
    assert(tree.show[Syntax] === "foo + bar // baz")
  }

  test("scala.meta.Tree.toString (quasiquotes)") {
    val tree = q"foo + bar // baz"
    assert(tree.toString === "foo + bar")
  }

  test("scala.meta.Tree.show[Structure] (quasiquoted)") {
    val tree = q"foo + bar // baz"
    assert(tree.show[Structure] === """Term.ApplyInfix(Term.Name("foo"), Term.Name("+"), Nil, Seq(Term.Name("bar")))""")
  }

  test("scala.meta.Tree.show[Syntax] (quasiquoted)") {
    val tree = q"foo + bar // baz"
    assert(tree.show[Syntax] === "foo + bar")
  }

  test("scala.meta.classifiers.Classifiable.toString") {
    // n/a
  }

  test("scala.meta.classifiers.Classifier.toString") {
    // n/a
  }

  test("scala.meta.common.Convert.toString") {
    // n/a
  }

  test("scala.meta.common.Optional.toString") {
    // n/a
  }

  test("scala.meta.dialects.Dotty.toString") {
    assert(scala.meta.dialects.Dotty.toString === "Dotty")
  }

  test("scala.meta.dialects.Sbt0136.toString") {
    assert(scala.meta.dialects.Sbt0136.toString === "Sbt0136")
  }

  test("scala.meta.dialects.Sbt0137.toString") {
    assert(scala.meta.dialects.Sbt0137.toString === "Sbt0137")
  }

  test("scala.meta.dialects.Scala210.toString") {
    assert(scala.meta.dialects.Scala210.toString === "Scala210")
  }

  test("scala.meta.dialects.Scala211.toString") {
    assert(scala.meta.dialects.Scala211.toString === "Scala211")
  }

  test("scala.meta.dialects.Scala212.toString") {
    assert(scala.meta.dialects.Scala212.toString === "Scala212")
  }

  test("scala.meta.dialects.Typelevel211.toString") {
    assert(scala.meta.dialects.Typelevel211.toString === "Typelevel211")
  }

  test("scala.meta.dialects.Typelevel212.toString") {
    assert(scala.meta.dialects.Typelevel212.toString === "Typelevel212")
  }

  test("scala.meta.dialects.Paradise211.toString") {
    assert(scala.meta.dialects.Paradise211.toString === "Paradise211")
  }

  test("scala.meta.dialects.Paradise212.toString") {
    assert(scala.meta.dialects.Paradise212.toString === "Paradise212")
  }

  test("scala.meta.dialects.ParadiseTypelevel211.toString") {
    assert(scala.meta.dialects.ParadiseTypelevel211.toString === "ParadiseTypelevel211")
  }

  test("scala.meta.dialects.ParadiseTypelevel212.toString") {
    assert(scala.meta.dialects.ParadiseTypelevel212.toString === "ParadiseTypelevel212")
  }

  test("scala.meta.io.PlatformIO") {}

  test("scala.meta.inputs.Input.toString") {
    // covered below
  }

  test("scala.meta.dialects.AllowEverything.toString") {
    // Satisfy surface suite.
  }

  test("scala.meta.inputs.Input.None.toString") {
    assert(Input.None.toString == "Input.None")
  }

  test("scala.meta.inputs.Input.File.toString") {
    import java.io._
    import java.nio.charset.Charset
    val path = AbsolutePath.fromRelative("hello.scala")
    val input1 = Input.File(path, Charset.forName("latin1"))
    val input2 = Input.File(path, Charset.forName("UTF-8"))
    assert(input1.toString == """Input.File(new File("hello.scala"), Charset.forName("ISO-8859-1"))""")
    assert(input2.toString == """Input.File(new File("hello.scala"), Charset.forName("UTF-8"))""")
  }

  test("scala.meta.inputs.Input.Slice.toString") {
    val input = Input.Slice(Input.String("foo"), 0, 2)
    assert(input.toString == """Input.Slice(Input.String("foo"), 0, 2)""")
  }

  test("scala.meta.inputs.Input.Stream.toString") {
    import java.io._
    import java.nio.charset.Charset
    val latin1 = Charset.forName("latin1")
    val stream = new ByteArrayInputStream("Привет(мир!)".getBytes(latin1))
    val input1 = Input.Stream(stream, latin1)
    val input2 = Input.Stream(stream, Charset.forName("UTF-8"))
    assert(input1.toString == """Input.Stream(<stream>, Charset.forName("ISO-8859-1"))""")
    assert(input2.toString == """Input.Stream(<stream>, Charset.forName("UTF-8"))""")
  }

  test("scala.meta.inputs.Input.String.toString") {
    val input = Input.String("foo")
    assert(input.toString == """Input.String("foo")""")
  }

  test("scala.meta.inputs.Point.toString") {
    // covered below
  }

  test("scala.meta.inputs.Point.None.toString") {
    assert(Point.None.toString == "Point.None")
  }

  test("scala.meta.inputs.Point.Offset.toString") {
    val Term.ApplyInfix(lhs, _, _, _) = "foo + bar".parse[Term].get
    assert(lhs.pos.start.toString === """0 in Input.String("foo + bar")""")
    assert(lhs.pos.end.toString === """3 in Input.String("foo + bar")""")
  }

  test("scala.meta.inputs.Position.toString") {
    // covered below
  }

  test("scala.meta.inputs.Position.None.toString") {
    assert(Position.None.toString == "Position.None")
  }

  test("scala.meta.inputs.Position.Range.toString") {
    val Term.ApplyInfix(lhs, _, _, _) = "foo + bar".parse[Term].get
    assert(lhs.pos.toString === """[0..3) in Input.String("foo + bar")""")
  }

  test("scala.meta.io.AbsolutePath.toString") {
    // n/a
  }

  test("scala.meta.parsers.Parse.toString") {
    // n/a
  }

  test("scala.meta.parsers.ParseException.toString") {
    intercept[ParseException] {
      try "foo + class".parse[Term].get
      catch {
        case ex: ParseException =>
          assert(ex.toString === """
            |<input>:1: error: end of file expected but class found
            |foo + class
            |      ^
          """.trim.stripMargin)
          throw ex
      }
    }
  }

  test("scala.meta.parsers.Parsed.toString") {
    // covered below
  }

  test("scala.meta.parsers.Parsed.Error.toString") {
    val parsed = "foo + class".parse[Term]
    assert(parsed.toString === """
      |<input>:1: error: end of file expected but class found
      |foo + class
      |      ^
    """.trim.stripMargin)
  }

  test("scala.meta.parsers.Parsed.Success.toString") {
    val parsed = "foo + bar".parse[Term]
    assert(parsed.toString === "foo + bar")
  }

  test("scala.meta.prettyprinters.LowPriorityOptions.Lazy.toString") {
    assert(scala.meta.prettyprinters.Options.Lazy.toString == "Lazy")
  }

  test("scala.meta.prettyprinters.Options.toString") {
    // covered above and below
  }

  test("scala.meta.prettyprinters.Options.Eager.toString") {
    assert(scala.meta.prettyprinters.Options.Eager.toString == "Eager")
  }

  test("scala.meta.prettyprinters.Show.toString") {
    // n/a
  }

  test("scala.meta.prettyprinters.Structure.toString") {
    // n/a
  }

  test("scala.meta.prettyprinters.Syntax.toString") {
    // n/a
  }

  test("scala.meta.quasiquotes.Lift.toString") {
    // n/a
  }

  test("scala.meta.quasiquotes.Unlift.toString") {
    // n/a
  }

  test("scala.meta.semantic.v1.Address.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Address.File.toString") {
    val file = "source.scala"
    val syntax = s"file:$file"
    val fullPath = AbsolutePath.fromRelative(file).absolute
    val address = scala.meta.semantic.v1.Address(syntax)
    val scala.meta.semantic.v1.Address.File(AbsolutePath(`fullPath`)) = address
    assert(address.toString === s"file:$fullPath")
  }

  test("scala.meta.semantic.v1.Address.Snippet.toString") {
    val syntax = "snippet:Int"
    val address = scala.meta.semantic.v1.Address(syntax)
    val scala.meta.semantic.v1.Address.Snippet("Int") = address
    assert(address.toString === syntax)
  }

  test("scala.meta.semantic.v1.Completed.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Completed.Error.toString") {
    // n/a
  }

  test("scala.meta.semantic.v1.Completed.Success.toString") {
    // n/a
  }

  test("scala.meta.semantic.v1.Database.toString") {
    // n/a
  }

  test("scala.meta.semantic.v1.Location.toString") {
    val file = "source.scala"
    val path = AbsolutePath.fromRelative(file).absolute
    val location = scala.meta.semantic.v1.Location(scala.meta.semantic.v1.Address(s"file:$file"), 40, 42)
    assert(location.toString === s"""Location(Address("file:$path"), 40, 42)""")
  }

  test("scala.meta.semantic.v1.Mirror.toString") {
    // n/a
  }

  test("scala.meta.semantic.v1.SemanticException.toString") {
    // n/a
  }

  test("scala.meta.semantic.v1.Signature.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Signature.Method.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Signature.Self.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Signature.Term.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Signature.TermParameter.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Signature.Type.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Signature.TypeParameter.toString") {
    // covered below
  }

  test("scala.meta.semantic.v1.Symbol.toString") {
    import scala.meta.semantic.v1.Address
    import scala.meta.semantic.v1.Symbol
    import scala.meta.semantic.v1.Signature

    val syntaxNone = ""
    val none @ Symbol.None = Symbol(syntaxNone)
    assert(none.toString === syntaxNone)

    val syntaxGlobalTerm = "_root_.scala."
    val globalTerm @ Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.Term("scala")) = Symbol(syntaxGlobalTerm)
    assert(globalTerm.toString === syntaxGlobalTerm)

    val syntaxGlobalType = "_root_.C#"
    val globalType @ Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.Type("C")) = Symbol(syntaxGlobalType)
    assert(globalType.toString === syntaxGlobalType)

    val syntaxGlobalMethod = "_root_.m(I)I."
    val globalMethod @ Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.Method("m", "(I)I")) = Symbol(syntaxGlobalMethod)
    assert(globalMethod.toString === syntaxGlobalMethod)

    val syntaxGlobalTermParameter = "_root_.(x)"
    val globalTermParameter @ Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.TermParameter("x")) = Symbol(syntaxGlobalTermParameter)
    assert(globalTermParameter.toString === syntaxGlobalTermParameter)

    val syntaxGlobalTypeParameter = "_root_.[T]"
    val globalTypeParameter @ Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.TypeParameter("T")) = Symbol(syntaxGlobalTypeParameter)
    assert(globalTypeParameter.toString === syntaxGlobalTypeParameter)

    val syntaxGlobalSelf = "_root_.self=>"
    val globalSelf @ Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.Self("self")) = Symbol(syntaxGlobalSelf)
    assert(globalSelf.toString === syntaxGlobalSelf)

    val file =  AbsolutePath.fromRelative("source.scala").absolute
    val syntaxLocal = s"file:$file@40..42"
    val local @ Symbol.Local(Address.File(AbsolutePath(path)), 40, 42) = Symbol(syntaxLocal)
    assert(local.toString === syntaxLocal)

    val syntaxMulti = "_root_.C#;_root.C."
    val multi @ Symbol.Multi(List(Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root_")), Signature.Type("C")), Symbol.Global(Symbol.Global(Symbol.None, Signature.Term("_root")), Signature.Term("C")))) = Symbol(syntaxMulti)
    assert(multi.toString === syntaxMulti)
  }

  test("scala.meta.semantic.v1.Symbol.Global.toString") {
    // covered above
  }

  test("scala.meta.semantic.v1.Symbol.Local.toString") {
    // covered above
  }

  test("scala.meta.semantic.v1.Symbol.Multi.toString") {
    // covered above
  }

  test("scala.meta.semantic.v1.Symbol.None.toString") {
    // covered above
  }

  test("scala.meta.tokenizers.Tokenize.toString") {
    // n/a
  }

  test("scala.meta.tokenizers.TokenizeException.toString") {
    intercept[TokenizeException] {
      try """"c""".tokenize.get
      catch {
        case ex: TokenizeException =>
          assert(ex.toString === """
            |<input>:1: error: unclosed string literal
            |"c
            |^
          """.trim.stripMargin)
          throw ex
      }
    }
  }

  test("scala.meta.tokenizers.Tokenized.Error.toString") {
    val tokenized = """"c""".tokenize
    assert(tokenized.toString === """
      |<input>:1: error: unclosed string literal
      |"c
      |^
    """.trim.stripMargin)
  }

  test("scala.meta.tokenizers.Tokenized.Success.toString") {
    val tokenized = "foo + bar".tokenize
    assert(tokenized.toString === "foo + bar")
  }

  test("scala.meta.tokens.Token.toString") {
    val token = "foo + bar".tokenize.get(1)
    assert(token.toString === "foo")
  }

  test("scala.meta.tokens.Token.show[Structure]") {
    val token = "foo + bar".tokenize.get(1)
    assert(token.show[Structure] === "foo [0..3)")
  }

  test("scala.meta.tokens.Token.show[Syntax]") {
    val token = "foo + bar".tokenize.get(1)
    assert(token.show[Syntax] === "foo")
  }

  test("scala.meta.tokens.Tokens.toString") {
    val tokens = "foo + bar".tokenize.get
    assert(tokens.toString === "foo + bar")
  }

  test("scala.meta.tokens.Tokens.show[Structure]") {
    val tokens = "foo + bar".tokenize.get
    assert(tokens.show[Structure] === "Tokens(BOF [0..0), foo [0..3),   [3..4), + [4..5),   [5..6), bar [6..9), EOF [9..9))")
  }

  test("scala.meta.tokens.Tokens.show[Syntax]") {
    val tokens = "foo + bar".tokenize.get
    assert(tokens.show[Syntax] === "foo + bar")
  }

  test("scala.meta.transversers.Transformer.toString") {
    // n/a
  }

  test("scala.meta.transversers.Traverser.toString") {
    // n/a
  }
  test("scala.meta.semantic.v1.Severity.Unknown") { }
  test("scala.meta.semantic.v1.Severity.Error") { }
  test("scala.meta.semantic.v1.Severity.Info") { }
  test("scala.meta.semantic.v1.Severity.Warning") { }
  test("scala.meta.semantic.v1.CompilerMessage") { }
}
