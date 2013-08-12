html:
	pandoc \
		--smart \
		--standalone \
		--highlight-style pygments \
		-f markdown \
		-t dzslides \
		-o scala_at_rackspace.html \
		scala_at_rackspace.md
	xdotool search --name Chromium key --window %@ F5 || true
	xdotool search --name Firefox key --window %@ F5 || true
	xdotool search --name Iceweasel key --window %@ F5 || true
	xdotool search --name Pentadactyl key --window %@ F5 || true

clean:
	rm -f scala_at_rackspace.html


html-loop:
	iwatch -e close_write -t '.+\.md|Makefile' -c make .


.PHONY: clean html
