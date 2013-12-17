# Makefile to assist with building Debian packages
#
# There are some targets that are required by Debhelper to know how to
# build/install the project with SBT.
#
# There are also some targets to help build Debian packages and test them with
# Lintian.


rootdir=$(realpath $(dir $(lastword $(MAKEFILE_LIST))))

sbt = $(rootdir)/sbt

assembly_stamp = $(rootdir)/target/assembly.make

server_jar_src = $(wildcard $(rootdir)/modules/server/target/scala-*/yourorg-yourapp-server-assembly-*.jar)
server_jar_dest = $(DESTDIR)/usr/share/yourorg/yourapp/lib/yourorg-yourapp-server.jar

server_deb = $(wildcard $(rootdir)/target/*.deb)


# targets required by Debhelper

$(assembly_stamp):
	cd "$(rootdir)"; "$(sbt)" assembly
	mkdir -p target
	touch "$@"

test:
	cd "$(rootdir)"; "$(sbt)" test

$(server_jar_dest): $(assembly_stamp)
	test -r "$(server_jar_src)"
	install -D --mode=0644 "$(server_jar_src)" "$@"

install: $(server_jar_dest)

clean:
	find "$(rootdir)" -type d -name target -exec rm -rf {} +


# useful targets for building Debian packages

debian-lint: debian
	lintian --verbose $(server_deb)

debian-lint-quick: debian-quick
	lintian --verbose $(server_deb)

debian:
	cd "$(rootdir)"; \
		DH_DESTDIR=target dpkg-buildpackage \
			-b -us -uc --changes-option=--version

debian-quick: debian-clean
	cd "$(rootdir)"; \
		DEB_BUILD_OPTIONS=nocheck DH_DESTDIR=target dpkg-buildpackage \
			-b -us -uc -nc --changes-option=--version

debian-clean:
	cd "$(rootdir)"; debian/rules override_dh_clean

debian-distclean: clean debian-clean


.PHONY: test install clean debian-lint debian-lint-quick debian debian-quick debian-clean debian-distclean
