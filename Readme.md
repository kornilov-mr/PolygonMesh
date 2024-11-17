# MSBuild support on C# WPF

This project is a little Ui application with support of MSBuild *.csproj* file

## How to run the project

To run a project simply clone it.

```bash
git clone https://github.com/kornilov-mr/MSBuildSupportWPF.git
```
There are 4 example of MSbuild in *./examples* folder (default folder for file chooser in the app)

## Features:
### Highlighting

Sintaxis based Highlighting. <br>
Every color can be edited in <kbd>.\MSBuildSupportWPF\resources\codeNodeColor\CodeNodeColorJson.json</kbd>

![Alt Text](https://github.com/kornilov-mr/WordBoundRewrite/blob/master/photo1.png).

### Error checks

Each time file is reparsed, it's context is check for parsing error (XmlExceptions), which are then presented to user with Error popup, along side different  highlighting.

![Alt Text](https://github.com/kornilov-mr/WordBoundRewrite/blob/master/gif2.gif)

### linked nodes

Parsing creates Stack of Nodes <kbd>.\MSBuildSupportWPF\code\BlockTree.cs</kbd>, which are then shown on the screen with different hightlight colors.<br>
Some of nodes are Linked (After editing one node every other linked to it node will be changed too)

![Alt Text](https://github.com/kornilov-mr/WordBoundRewrite/blob/master/gif1.gif)