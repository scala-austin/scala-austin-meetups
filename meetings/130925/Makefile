loop:
	iwatch -r -e close_write -t '.' -c 'make refresh' .


refresh:
	xdotool search --name Chromium key --window %@ F5 || true
	xdotool search --name Firefox key --window %@ F5 || true
	xdotool search --name Iceweasel key --window %@ F5 || true
	xdotool search --name Pentadactyl key --window %@ F5 || true
