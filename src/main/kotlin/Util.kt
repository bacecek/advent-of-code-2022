fun loadInput(resourceName: String): String {
    return {}::javaClass.get().classLoader.getResource(resourceName)!!.readText()
}