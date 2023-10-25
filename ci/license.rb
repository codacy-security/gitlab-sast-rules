#!/usr/bin/env ruby
# frozen_string_literal: true

require 'fileutils'

# License
module License

  MIT = {
    id: :MIT,
    name: 'MIT',
    dir: ''
  }.freeze

  SEMGREP = {
    id: :EXT_SEMGREP,
    name: 'Commons Clause License Condition v1.0[LGPL-2.1-only]',
    dir: 'external-semgrep'
  }.freeze

  ALL = [MIT, SEMGREP].freeze

  def self.copy_license_files(dist_dir)
    ALL.each do |license|
      license_dir = license[:dir]

      next unless File.exist?(File.join(dist_dir, license_dir))

      src_license = File.join('ci', 'license_templates', license[:id].to_s)
      target_path = File.join(dist_dir, license_dir, 'LICENSE')

      puts "copying '#{license[:name]}' license file into 'dist/#{license_dir}' directory"

      FileUtils.cp(src_license, target_path)
    end
  end

  def self.by_name(name)
    ALL.each do |l|
      return l if l[:name] == name
    end
  end

end