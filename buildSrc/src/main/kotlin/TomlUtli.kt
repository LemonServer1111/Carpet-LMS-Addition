import org.tomlj.Toml
import java.io.File

fun readToml(file: File) = Toml.parse(file.readText())
