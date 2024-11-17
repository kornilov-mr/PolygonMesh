# Simple polygon editor

This project is a little Ui application, an editor for polygon 3d models written from scratch using java and jocl with c for shaders


## How to run the project

To run a project simply clone it.

```bash
git clone https://github.com/kornilov-mr/MSBuildSupportWPF.git
```
There are 4 example of MSbuild in *./examples* folder (default folder for file chooser in the app)

## Features:
### 3D orientation

3D movement based on direction vectors and sphere orientation.

![Alt Text](https://github.com/kornilov-mr/WordBoundRewrite/blob/master/photo1.png).

### Primitive editing

Ability to Select Primitives and then edit their parameters (for example color and point's position)

![Alt Text](https://github.com/kornilov-mr/WordBoundRewrite/blob/master/gif2.gif)

### Change stack

Ability to reverse Changes, which have been created recently. Achieved through making all actions a command, tracking all created and edited Primitive by the command

### Instructions

Whenever User tries to execute operation, which need addition information, where will be a popup requesting user to specify additional parameters. All requests are in a stuck.

### Hot Keys

All of the Hot keys can be changed in the application
* <kbd>shift</kbd>+<kbd>P</kbd> - create a Point
* <kbd>shift</kbd>+<kbd>[</kbd> - create a Polygon
* <kbd>shift</kbd>+<kbd>R</kbd> - rotate selected objects
* <kbd>shift</kbd>+<kbd>R</kbd> - rotate selected objects
* <kbd>cntr</kbd> - switch to only polygon mod
* <kbd>shift</kbd>+<kbd>A</kbd> - create a Polygon with 3 Selected Points
* <kbd>cntr</kbd>+<kbd>Z</kbd> - reverse changes
* <kbd>cntl</kbd>+<kbd>shift</kbd>+<kbd>Z</kbd> - undo change reversing

