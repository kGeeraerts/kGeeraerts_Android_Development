# Android Development project

## Accounts and seeding

On first startup, the db should include 2 usable accounts:
1. Username: StartUser , pw: 12345
2. Username: SupportingCast , pw: 12345

More users can be added via the "Register" functionality.

## Memes

Only localy stored memes can be added to the list of available memes. To add these memes on hosted android devices, open the "gallery" on your device (or another location) and slide the files from you PC into your device-gallery.

## Hashing

All passwords are hashed with BCrypt for aditional security.
