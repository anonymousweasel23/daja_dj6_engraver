# Daja dj6 engraver s/w

## Summary
This is a quick and dirty attampt to get the s/w for the daja dj6 engraver to work

The code was *not* nice and fernflower no doublt mangled it further. The patching mangles it further still. Will require extensive work to clean this up properly. There is a lot of random stuff in here which has not been touched.

### Changes
Main change is to remove the 'automated' search for connection ports and search for magic strings.

Changes

 - Change to serial connection - allow user to pick serial port
 - Save default port once set in properties file
 - Change to thread handling of serial reader to be slightly more sane
 - Anglicisation of some class/field names to help (me) get my head around this stuff

### Breaking updates
 - Some of the classes contain artifacts which have been manually patched
 - There are numerous classes that look like they have copied and pasted into the original source 
 - Changed main package name from 'examples' to 'com.engraver.ui'

## Usage
java -jar engraver.jar
