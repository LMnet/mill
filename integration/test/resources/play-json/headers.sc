//import $ivy.`com.github.rockjam::license-headers:0.0.1`
//
//
//import mill._, scalalib._
//import mill.api.PathRef
//import de.heikoseeberger.sbtheader.{CommentStyle, FileType, HeaderCreator, License}
//
//trait Headers extends ScalaModule {
//
//  val headerMappings = Map(
//    FileType.scala.extension -> (FileType.scala, CommentStyle.cStyleBlockComment),
//    FileType.java.extension -> (FileType.java, CommentStyle.cStyleBlockComment)
//  )
//
//  val license = {
//    val currentYear = java.time.Year.now(java.time.Clock.systemUTC).getValue
//    License.Custom(
//      s"Copyright (C) 2009-$currentYear Lightbend Inc. <https://www.lightbend.com>"
//    )
//  }
//
//  def headerCreate() = T.command {
//    val withoutHeaders = filesWithoutHeaders(sources() ++ resources())
//
//    val updatedFiles = withoutHeaders.map {
//      case (file, updated) =>
//        os.write.over(file, updated)
//        file
//    }
//
//    if (updatedFiles.nonEmpty) {
//      T.ctx.log.info(
//        s"Headers created for ${updatedFiles.size} files:\n${updatedFiles.mkString("\n")}")
//    }
//    updatedFiles
//  }
//
//  def headerCheck() = T.command {
//    val withoutHeaders = filesWithoutHeaders(sources() ++ resources()).map(_._1)
//
//    if (withoutHeaders.nonEmpty) {
//      sys.error(
//        s"There are files without headers!\n${withoutHeaders.mkString("\n")}"
//      )
//    }
//  }
//
//  private def filesWithoutHeaders(input: Seq[PathRef]) = {
//    for {
//      ref <- input if os.exists(ref.path)
//      file <- os.walk(ref.path) if os.isFile(file)
//      (fileType, commentStyle) <- headerMappings.get(file.ext)
//      updatedContent <- HeaderCreator(
//        fileType,
//        commentStyle,
//        license,
//        log = _ => (),
//        os.read.inputStream(file)
//      ).createText
//    } yield file -> updatedContent
//  }
//
//}
